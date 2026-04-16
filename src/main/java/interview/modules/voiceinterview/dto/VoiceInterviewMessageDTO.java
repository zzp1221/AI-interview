package interview.modules.voiceinterview.dto;

import java.time.LocalDateTime;

public record VoiceInterviewMessageDTO(
        Long id,
        Long sessionId,
        String messageType,
        String phase,
        String userRecognizedText,
        String aiGeneratedText,
        LocalDateTime timestamp,
        Integer sequenceNum
) {
}

