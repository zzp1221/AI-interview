package interview.modules.voiceinterview.dto;

public record VoiceEvaluationStatusDTO(
        String evaluateStatus,
        String evaluateError,
        VoiceEvaluationDetailDTO evaluation
) {
}

