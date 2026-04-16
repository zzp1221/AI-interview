package interview.modules.voiceinterview.dto;

public record CreateSessionRequest(
        String roleType,
        String skillId,
        String difficulty,
        String customJdText,
        Long resumeId,
        Boolean introEnabled,
        Boolean techEnabled,
        Boolean projectEnabled,
        Boolean hrEnabled,
        Integer plannedDuration,
        String llmProvider
) {
}

