package interview.modules.voiceinterview.dto;

import java.util.Map;

public record WebSocketControlMessage(
        String type,
        String action,
        String phase,
        Map<String, Object> data
) {
}

