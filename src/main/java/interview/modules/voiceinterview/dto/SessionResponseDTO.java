package interview.modules.voiceinterview.dto;

import java.time.LocalDateTime;

public record SessionResponseDTO(
        Long sessionId,
        String roleType,
        String currentPhase,
        String status,
        LocalDateTime startTime,
        Integer plannedDuration,
        String webSocketUrl
) {
}

