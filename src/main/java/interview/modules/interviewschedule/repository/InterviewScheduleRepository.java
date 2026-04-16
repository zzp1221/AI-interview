package interview.modules.interviewschedule.repository;

import interview.modules.interviewschedule.model.InterviewScheduleEntity;
import interview.modules.interviewschedule.model.InterviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface InterviewScheduleRepository extends JpaRepository<InterviewScheduleEntity, Long> {
    List<InterviewScheduleEntity> findByUserIdOrderByInterviewTimeAsc(Long userId);

    List<InterviewScheduleEntity> findByUserIdAndStatusOrderByInterviewTimeAsc(Long userId, InterviewStatus status);

    List<InterviewScheduleEntity> findByUserIdAndInterviewTimeBetweenOrderByInterviewTimeAsc(
            Long userId,
            LocalDateTime start,
            LocalDateTime end
    );

    Optional<InterviewScheduleEntity> findByIdAndUserId(Long id, Long userId);
}

