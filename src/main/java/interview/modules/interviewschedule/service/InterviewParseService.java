package interview.modules.interviewschedule.service;

import interview.modules.interviewschedule.model.CreateInterviewScheduleRequest;
import interview.modules.interviewschedule.model.ParseResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InterviewParseService {

    private static final Pattern COMPANY_PATTERN = Pattern.compile("(?:公司|单位)[：:]\\s*([^\\s\\n]{1,50})");
    private static final Pattern POSITION_PATTERN = Pattern.compile("(?:岗位|职位)[：:]\\s*([^\\s\\n]{1,50})");
    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{4}[-/]\\d{2}[-/]\\d{2})\\s+(\\d{2}:\\d{2})");
    private static final Pattern LINK_PATTERN = Pattern.compile("https?://[^\\s\\n]+");

    public ParseResponse parse(String rawText, String source) {
        if (rawText == null || rawText.isBlank()) {
            return new ParseResponse(false, null, 0.0, "none", "输入文本为空");
        }

        String company = match(COMPANY_PATTERN, rawText);
        String position = match(POSITION_PATTERN, rawText);
        LocalDateTime time = parseDateTime(rawText);
        String link = match(LINK_PATTERN, rawText);
        String type = detectType(rawText);

        if (company == null || position == null || time == null) {
            return new ParseResponse(false, null, 0.0, "rule", "关键信息不足，建议手动填写");
        }

        CreateInterviewScheduleRequest request = new CreateInterviewScheduleRequest(
                company,
                position,
                time,
                type,
                link,
                1,
                null,
                source == null ? "来源: unknown" : "来源: " + source
        );
        return new ParseResponse(true, request, 0.85, "rule", "规则解析成功");
    }

    private String match(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(1).trim() : null;
    }

    private LocalDateTime parseDateTime(String text) {
        Matcher matcher = TIME_PATTERN.matcher(text);
        if (!matcher.find()) {
            return null;
        }
        String date = matcher.group(1).replace("/", "-");
        String time = matcher.group(2);
        return LocalDateTime.parse(date + "T" + time + ":00");
    }

    private String detectType(String text) {
        String lower = text.toLowerCase();
        if (lower.contains("电话") || lower.contains("phone")) {
            return "PHONE";
        }
        if (lower.contains("现场") || lower.contains("onsite")) {
            return "ONSITE";
        }
        return "VIDEO";
    }
}

