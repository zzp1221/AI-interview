package interview.modules.interview.model;

import interview.common.model.AsyncTaskStatus;

import java.time.LocalDateTime;

public record SessionListItemDTO(
        String sessionId,
        Long resumeId,
        int totalQuestions,
        String status,
        String evaluateStatus,
        String evaluateError,
        Integer overallScore,
        LocalDateTime createdAt,
        LocalDateTime completedAt
) {
    public static SessionListItemDTO from(InterviewSessionEntity entity) {
        AsyncTaskStatus evaluateStatus = entity.getEvaluateStatus();
        return new SessionListItemDTO(
                entity.getSessionId(),
                entity.getResume() != null ? entity.getResume().getId() : null,
                entity.getTotalQuestions() == null ? 0 : entity.getTotalQuestions(),
                entity.getStatus() == null ? null : entity.getStatus().name(),
                evaluateStatus == null ? null : evaluateStatus.name(),
                entity.getEvaluateError(),
                entity.getOverallScore(),
                entity.getCreatedAt(),
                entity.getCompletedAt()
        );
    }
}

