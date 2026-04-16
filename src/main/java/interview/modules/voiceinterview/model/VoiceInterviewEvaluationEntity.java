package interview.modules.voiceinterview.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "voice_interview_evaluations", indexes = {
        @Index(name = "idx_voice_eval_session", columnList = "sessionId", unique = true)
})
public class VoiceInterviewEvaluationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long sessionId;

    private Integer overallScore;

    @Column(columnDefinition = "TEXT")
    private String overallFeedback;
    @Column(columnDefinition = "TEXT")
    private String strengthsJson;
    @Column(columnDefinition = "TEXT")
    private String improvementsJson;
    @Column(columnDefinition = "TEXT")
    private String answersJson;

    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    protected void onUpsert() {
        updatedAt = LocalDateTime.now();
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

    public Integer getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Integer overallScore) {
        this.overallScore = overallScore;
    }

    public String getOverallFeedback() {
        return overallFeedback;
    }

    public void setOverallFeedback(String overallFeedback) {
        this.overallFeedback = overallFeedback;
    }

    public String getStrengthsJson() {
        return strengthsJson;
    }

    public void setStrengthsJson(String strengthsJson) {
        this.strengthsJson = strengthsJson;
    }

    public String getImprovementsJson() {
        return improvementsJson;
    }

    public void setImprovementsJson(String improvementsJson) {
        this.improvementsJson = improvementsJson;
    }

    public String getAnswersJson() {
        return answersJson;
    }

    public void setAnswersJson(String answersJson) {
        this.answersJson = answersJson;
    }
}

