package interview.modules.voiceinterview.dto;

import java.time.LocalDateTime;

public record SessionMetaDTO(
        Long sessionId,
        String roleType,
        String status,
        String currentPhase,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Integer actualDuration,
        Long messageCount,
        String evaluateStatus,
        String evaluateError
) {
}

