package interview.modules.voiceinterview.dto;

import java.util.List;

public record VoiceEvaluationDetailDTO(
        Long sessionId,
        Integer totalQuestions,
        Integer overallScore,
        String overallFeedback,
        List<String> strengths,
        List<String> improvements,
        List<AnswerDetail> answers
) {
    public record AnswerDetail(
            Integer questionIndex,
            String question,
            String category,
            String userAnswer,
            Integer score,
            String feedback,
            String referenceAnswer,
            List<String> keyPoints
    ) {
    }
}

