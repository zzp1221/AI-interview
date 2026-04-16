package interview.modules.interview.skill;

import interview.common.ai.StructuredOutputInvoker;
import interview.common.exception.BusinessException;
import interview.common.exception.ErrorCode;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class InterviewSkillService {

    public static final String CUSTOM_SKILL_ID = "custom";
    private static final int MIN_JD_LENGTH = 50;
    private static final Pattern NON_WORD = Pattern.compile("[^A-Z0-9]+");

    private final ChatClient chatClient;
    private final StructuredOutputInvoker structuredOutputInvoker;
    private final PromptTemplate jdParsePromptTemplate;
    private final BeanOutputConverter<CategoryListDTO> outputConverter;
    private final Map<String, SkillDTO> presetSkills;
    private final String referenceFileList;

    public InterviewSkillService(
            ChatClient.Builder chatClientBuilder,
            StructuredOutputInvoker structuredOutputInvoker,
            @Value("classpath:prompts/jd-parse-system.st") Resource jdParsePrompt
    ) throws IOException {
        this.chatClient = chatClientBuilder.build();
        this.structuredOutputInvoker = structuredOutputInvoker;
        this.jdParsePromptTemplate = new PromptTemplate(jdParsePrompt.getContentAsString(StandardCharsets.UTF_8));
        this.outputConverter = new BeanOutputConverter<>(CategoryListDTO.class);
        this.presetSkills = buildPresetSkills();
        this.referenceFileList = buildReferenceFileList();
    }

    public List<SkillDTO> getAllSkills() {
        return new ArrayList<>(presetSkills.values());
    }

    public SkillDTO getSkill(String id) {
        SkillDTO skill = presetSkills.get(id);
        if (skill == null) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "未找到面试方向: " + id);
        }
        return skill;
    }

    public List<CategoryDTO> parseJd(String jdText) {
        if (jdText == null || jdText.trim().length() < MIN_JD_LENGTH) {
            throw new BusinessException(
                    ErrorCode.BAD_REQUEST,
                    "JD 内容太少（至少 " + MIN_JD_LENGTH + " 字），请补充后重试"
            );
        }

        String systemPrompt = jdParsePromptTemplate.render(Map.of(
                "referenceFileList", referenceFileList
        )) + "\n\n" + outputConverter.getFormat();
        String userPrompt = "职位描述：\n" + jdText;

        try {
            CategoryListDTO dto = structuredOutputInvoker.invoke(
                    chatClient,
                    systemPrompt,
                    userPrompt,
                    outputConverter,
                    ErrorCode.AI_SERVICE_ERROR,
                    "JD 解析失败：",
                    "JD 解析",
                    org.slf4j.LoggerFactory.getLogger(InterviewSkillService.class)
            );
            List<CategoryDTO> categories = normalizeCategories(dto != null ? dto.categories() : List.of());
            if (!categories.isEmpty()) {
                return categories;
            }
        } catch (Exception ignore) {
            // 降级走关键词解析，避免前端流程中断
        }

        return keywordFallback(jdText);
    }

    private List<CategoryDTO> normalizeCategories(List<CategoryDTO> source) {
        if (source == null || source.isEmpty()) {
            return List.of();
        }
        LinkedHashMap<String, CategoryDTO> dedup = new LinkedHashMap<>();
        for (CategoryDTO c : source) {
            if (c == null) {
                continue;
            }
            String key = normalizeKey(c.key(), c.label());
            if (key.isBlank()) {
                continue;
            }
            String label = (c.label() == null || c.label().isBlank()) ? key : c.label().trim();
            String priority = normalizePriority(c.priority());
            dedup.putIfAbsent(key, new CategoryDTO(key, label, priority, c.ref(), c.shared()));
            if (dedup.size() >= 7) {
                break;
            }
        }
        if (dedup.size() < 3) {
            return List.of();
        }
        return new ArrayList<>(dedup.values());
    }

    private List<CategoryDTO> keywordFallback(String jdText) {
        String text = jdText.toLowerCase(Locale.ROOT);
        List<CategoryDTO> categories = new ArrayList<>();
        categories.add(new CategoryDTO("PROJECT", "项目经验", "CORE", "distributed.md", true));
        if (text.contains("java") || text.contains("spring")) {
            categories.add(new CategoryDTO("JAVA_BACKEND", "Java后端", "CORE", "java.md", true));
            categories.add(new CategoryDTO("SPRING", "Spring体系", "NORMAL", "spring.md", true));
        }
        if (text.contains("mysql") || text.contains("sql")) {
            categories.add(new CategoryDTO("MYSQL", "MySQL", "NORMAL", "mysql.md", true));
        }
        if (text.contains("redis") || text.contains("缓存")) {
            categories.add(new CategoryDTO("REDIS", "Redis", "NORMAL", "redis.md", true));
        }
        if (text.contains("react") || text.contains("vue") || text.contains("前端")) {
            categories.add(new CategoryDTO("FRONTEND", "前端工程", "NORMAL", "react-vue.md", true));
        }
        while (categories.size() < 3) {
            categories.add(new CategoryDTO("SYSTEM_DESIGN", "系统设计", "NORMAL", "system-design-scenarios.md", true));
        }
        return categories.stream().distinct().limit(7).toList();
    }

    private String normalizePriority(String priority) {
        if (priority == null) {
            return "NORMAL";
        }
        String p = priority.trim().toUpperCase(Locale.ROOT);
        return "CORE".equals(p) ? "CORE" : "NORMAL";
    }

    private String normalizeKey(String key, String label) {
        String raw = (key != null && !key.isBlank()) ? key : (label == null ? "" : label);
        String upper = raw.trim().toUpperCase(Locale.ROOT);
        upper = NON_WORD.matcher(upper).replaceAll("_");
        upper = upper.replaceAll("_{2,}", "_").replaceAll("^_|_$", "");
        return upper;
    }

    private Map<String, SkillDTO> buildPresetSkills() {
        LinkedHashMap<String, SkillDTO> map = new LinkedHashMap<>();
        map.put("java-backend", new SkillDTO(
                "java-backend",
                "Java 后端",
                "面向 Java 后端岗位的综合面试方向",
                List.of(
                        new CategoryDTO("JAVA_BASIC", "Java基础", "CORE", "java.md", true),
                        new CategoryDTO("SPRING", "Spring体系", "CORE", "spring.md", true),
                        new CategoryDTO("MYSQL", "MySQL", "NORMAL", "mysql.md", true),
                        new CategoryDTO("REDIS", "Redis", "NORMAL", "redis.md", true),
                        new CategoryDTO("SYSTEM_DESIGN", "系统设计", "NORMAL", "system-design-scenarios.md", true)
                ),
                true,
                null
        ));
        map.put("frontend", new SkillDTO(
                "frontend",
                "前端工程",
                "面向前端工程岗位的综合面试方向",
                List.of(
                        new CategoryDTO("JAVASCRIPT", "JavaScript/TypeScript", "CORE", "javascript.md", true),
                        new CategoryDTO("REACT_VUE", "React/Vue", "CORE", "react-vue.md", true),
                        new CategoryDTO("BROWSER", "浏览器原理", "NORMAL", "browser.md", true),
                        new CategoryDTO("NETWORK", "网络基础", "NORMAL", "network-os.md", true),
                        new CategoryDTO("SYSTEM_DESIGN", "系统设计", "NORMAL", "system-design-scenarios.md", true)
                ),
                true,
                null
        ));
        map.put("python-backend", new SkillDTO(
                "python-backend",
                "Python 后端",
                "面向 Python 后端岗位的综合面试方向",
                List.of(
                        new CategoryDTO("PYTHON_BASIC", "Python基础", "CORE", "python-basic.md", true),
                        new CategoryDTO("DJANGO_FLASK", "Django/Flask", "CORE", "django-flask.md", true),
                        new CategoryDTO("DATABASE", "数据库", "NORMAL", "database.md", true),
                        new CategoryDTO("REDIS", "Redis", "NORMAL", "redis.md", true),
                        new CategoryDTO("SYSTEM_DESIGN", "系统设计", "NORMAL", "system-design-scenarios.md", true)
                ),
                true,
                null
        ));
        return map;
    }

    private String buildReferenceFileList() {
        StringBuilder sb = new StringBuilder();
        sb.append("| 文件名 | 范围 | 覆盖内容 |\n");
        sb.append("|--------|------|----------|\n");
        sb.append("| java.md | shared | Java 基础与核心机制 |\n");
        sb.append("| spring.md | shared | Spring 生态与原理 |\n");
        sb.append("| mysql.md | shared | MySQL 设计与优化 |\n");
        sb.append("| redis.md | shared | Redis 场景与原理 |\n");
        sb.append("| javascript.md | shared | JavaScript 语言机制 |\n");
        sb.append("| react-vue.md | shared | React/Vue 工程实践 |\n");
        sb.append("| browser.md | shared | 浏览器与渲染机制 |\n");
        sb.append("| system-design-scenarios.md | shared | 系统设计场景 |\n");
        sb.append("| python-basic.md | shared | Python 语言基础 |\n");
        sb.append("| django-flask.md | shared | Python Web 框架 |\n");
        sb.append("| database.md | shared | 通用数据库能力 |\n");
        sb.append("| network-os.md | shared | 网络与操作系统 |\n");
        sb.append("| distributed.md | shared | 分布式通识 |\n");
        return sb.toString();
    }

    private record CategoryListDTO(List<CategoryDTO> categories) {
    }

    public record SkillDTO(
            String id,
            String name,
            String description,
            List<CategoryDTO> categories,
            boolean isPreset,
            String sourceJd
    ) {
    }

    public record CategoryDTO(
            String key,
            String label,
            String priority,
            String ref,
            Boolean shared
    ) {
    }
}

