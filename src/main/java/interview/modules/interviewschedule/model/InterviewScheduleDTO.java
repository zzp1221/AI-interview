package interview.modules.interviewschedule.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record InterviewScheduleDTO(
        Long id,
        String companyName,
        String position,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime interviewTime,
        String interviewType,
        String meetingLink,
        Integer roundNumber,
        String interviewer,
        String notes,
        InterviewStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static InterviewScheduleDTO from(InterviewScheduleEntity entity) {
        return new InterviewScheduleDTO(
                entity.getId(),
                entity.getCompanyName(),
                entity.getPosition(),
                entity.getInterviewTime(),
                entity.getInterviewType(),
                entity.getMeetingLink(),
                entity.getRoundNumber(),
                entity.getInterviewer(),
                entity.getNotes(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}

