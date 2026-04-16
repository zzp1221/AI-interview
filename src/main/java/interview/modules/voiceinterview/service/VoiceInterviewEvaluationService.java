package interview.modules.voiceinterview.service;

import interview.common.exception.BusinessException;
import interview.common.exception.ErrorCode;
import interview.modules.voiceinterview.dto.VoiceEvaluationDetailDTO;
import interview.modules.voiceinterview.model.VoiceInterviewEvaluationEntity;
import interview.modules.voiceinterview.model.VoiceInterviewMessageEntity;
import interview.modules.voiceinterview.repository.VoiceInterviewEvaluationRepository;
import interview.modules.voiceinterview.repository.VoiceInterviewMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoiceInterviewEvaluationService {

    private final VoiceInterviewEvaluationRepository evaluationRepository;
    private final VoiceInterviewMessageRepository messageRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public VoiceEvaluationDetailDTO generateEvaluation(Long sessionId) {
        List<VoiceInterviewMessageEntity> messages = messageRepository.findBySessionIdOrderBySequenceNumAsc(sessionId);
        List<VoiceEvaluationDetailDTO.AnswerDetail> answers = new ArrayList<>();
        int totalScore = 0;
        int idx = 0;

        for (VoiceInterviewMessageEntity msg : messages) {
            if (msg.getUserRecognizedText() == null || msg.getUserRecognizedText().isBlank()) {
                continue;
            }
            int score = scoreByLength(msg.getUserRecognizedText());
            totalScore += score;
            answers.add(new VoiceEvaluationDetailDTO.AnswerDetail(
                    idx,
                    msg.getAiGeneratedText() == null ? "追问" : msg.getAiGeneratedText(),
                    msg.getPhase() == null ? "综合" : msg.getPhase(),
                    msg.getUserRecognizedText(),
                    score,
                    score >= 75 ? "回答结构较清晰，建议补充量化结果。" : "建议先给结论，再说明方案与取舍。",
                    null,
                    null
            ));
            idx++;
        }

        int questionCount = answers.size();
        int overall = questionCount == 0 ? 0 : Math.round((float) totalScore / questionCount);
        List<String> strengths = questionCount == 0
                ? List.of("暂无有效答题记录")
                : List.of("表达连贯性较好", "能结合经历进行回答");
        List<String> improvements = questionCount == 0
                ? List.of("先完成至少一轮有效问答")
                : List.of("回答中增加量化指标", "多解释技术取舍和边界场景");

        VoiceEvaluationDetailDTO dto = new VoiceEvaluationDetailDTO(
                sessionId,
                questionCount,
                overall,
                questionCount == 0 ? "本次未形成有效语音问答。" : "整体表现中等偏上，建议强化深度追问场景下的结构化表达。",
                strengths,
                improvements,
                answers
        );

        saveEvaluation(dto);
        return dto;
    }

    public VoiceEvaluationDetailDTO getEvaluation(Long sessionId) {
        VoiceInterviewEvaluationEntity entity = evaluationRepository.findBySessionId(sessionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "评估结果不存在: " + sessionId));
        try {
            List<VoiceEvaluationDetailDTO.AnswerDetail> answers = objectMapper.readValue(
                    entity.getAnswersJson(), new TypeReference<List<VoiceEvaluationDetailDTO.AnswerDetail>>() {});
            return new VoiceEvaluationDetailDTO(
                    sessionId,
                    answers.size(),
                    entity.getOverallScore() == null ? 0 : entity.getOverallScore(),
                    entity.getOverallFeedback(),
                    objectMapper.readValue(entity.getStrengthsJson(), new TypeReference<List<String>>() {}),
                    objectMapper.readValue(entity.getImprovementsJson(), new TypeReference<List<String>>() {}),
                    answers
            );
        } catch (Exception e) {
            log.error("反序列化语音评估结果失败: sessionId={}", sessionId, e);
            throw new BusinessException(ErrorCode.INTERNAL_ERROR, "评估结果读取失败");
        }
    }

    private void saveEvaluation(VoiceEvaluationDetailDTO dto) {
        try {
            VoiceInterviewEvaluationEntity entity = evaluationRepository.findBySessionId(dto.sessionId())
                    .orElseGet(VoiceInterviewEvaluationEntity::new);
            entity.setSessionId(dto.sessionId());
            entity.setOverallScore(dto.overallScore());
            entity.setOverallFeedback(dto.overallFeedback());
            entity.setStrengthsJson(objectMapper.writeValueAsString(dto.strengths()));
            entity.setImprovementsJson(objectMapper.writeValueAsString(dto.improvements()));
            entity.setAnswersJson(objectMapper.writeValueAsString(dto.answers()));
            evaluationRepository.save(entity);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_ERROR, "评估结果保存失败");
        }
    }

    private int scoreByLength(String text) {
        int len = text == null ? 0 : text.trim().length();
        if (len >= 180) return 90;
        if (len >= 120) return 82;
        if (len >= 80) return 74;
        if (len >= 40) return 65;
        return 55;
    }
}
