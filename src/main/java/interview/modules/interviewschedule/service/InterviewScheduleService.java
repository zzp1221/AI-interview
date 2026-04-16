package interview.modules.interviewschedule.service;

import interview.common.exception.BusinessException;
import interview.common.exception.ErrorCode;
import interview.modules.interviewschedule.model.CreateInterviewScheduleRequest;
import interview.modules.interviewschedule.model.InterviewScheduleDTO;
import interview.modules.interviewschedule.model.InterviewScheduleEntity;
import interview.modules.interviewschedule.model.InterviewStatus;
import interview.modules.interviewschedule.repository.InterviewScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewScheduleService {

    private final InterviewScheduleRepository repository;

    @Transactional
    public InterviewScheduleDTO create(Long userId, CreateInterviewScheduleRequest request) {
        InterviewScheduleEntity entity = new InterviewScheduleEntity();
        apply(entity, request);
        entity.setUserId(userId);
        entity.setStatus(InterviewStatus.PENDING);
        return InterviewScheduleDTO.from(repository.save(entity));
    }

    @Transactional
    public InterviewScheduleDTO update(Long userId, Long id, CreateInterviewScheduleRequest request) {
        InterviewScheduleEntity entity = getByIdOrThrow(userId, id);
        apply(entity, request);
        return InterviewScheduleDTO.from(repository.save(entity));
    }

    @Transactional
    public void delete(Long userId, Long id) {
        InterviewScheduleEntity entity = getByIdOrThrow(userId, id);
        repository.delete(entity);
    }

    @Transactional
    public InterviewScheduleDTO updateStatus(Long userId, Long id, InterviewStatus status) {
        InterviewScheduleEntity entity = getByIdOrThrow(userId, id);
        entity.setStatus(status);
        return InterviewScheduleDTO.from(repository.save(entity));
    }

    public List<InterviewScheduleDTO> getAll(Long userId, String status, LocalDateTime start, LocalDateTime end) {
        List<InterviewScheduleEntity> entities;
        if (start != null && end != null) {
            entities = repository.findByUserIdAndInterviewTimeBetweenOrderByInterviewTimeAsc(userId, start, end);
        } else if (status != null && !status.isBlank()) {
            InterviewStatus interviewStatus;
            try {
                interviewStatus = InterviewStatus.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new BusinessException(ErrorCode.BAD_REQUEST, "无效状态: " + status);
            }
            entities = repository.findByUserIdAndStatusOrderByInterviewTimeAsc(userId, interviewStatus);
        } else {
            entities = repository.findByUserIdOrderByInterviewTimeAsc(userId);
        }
        return entities.stream().map(InterviewScheduleDTO::from).toList();
    }

    public InterviewScheduleDTO getById(Long userId, Long id) {
        return InterviewScheduleDTO.from(getByIdOrThrow(userId, id));
    }

    private InterviewScheduleEntity getByIdOrThrow(Long userId, Long id) {
        return repository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "面试日程不存在: " + id));
    }

    private void apply(InterviewScheduleEntity entity, CreateInterviewScheduleRequest request) {
        entity.setCompanyName(request.companyName());
        entity.setPosition(request.position());
        entity.setInterviewTime(request.interviewTime());
        entity.setInterviewType(request.interviewType());
        entity.setMeetingLink(request.meetingLink());
        entity.setRoundNumber(request.roundNumber() == null ? 1 : request.roundNumber());
        entity.setInterviewer(request.interviewer());
        entity.setNotes(request.notes());
    }
}

