package interview.modules.voiceinterview.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "voice_interview_messages", indexes = {
        @Index(name = "idx_voice_msg_session_seq", columnList = "sessionId,sequenceNum")
})
public class VoiceInterviewMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long sessionId;

    private String messageType;
    private String phase;

    @Column(columnDefinition = "TEXT")
    private String userRecognizedText;
    @Column(columnDefinition = "TEXT")
    private String aiGeneratedText;

    private Integer sequenceNum;
    private LocalDateTime timestamp;

    @PrePersist
    protected void onCreate() {
        if (timestamp == null) {
            timestamp = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getUserRecognizedText() {
        return userRecognizedText;
    }

    public void setUserRecognizedText(String userRecognizedText) {
        this.userRecognizedText = userRecognizedText;
    }

    public String getAiGeneratedText() {
        return aiGeneratedText;
    }

    public void setAiGeneratedText(String aiGeneratedText) {
        this.aiGeneratedText = aiGeneratedText;
    }

    public Integer getSequenceNum() {
        return sequenceNum;
    }

    public void setSequenceNum(Integer sequenceNum) {
        this.sequenceNum = sequenceNum;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

