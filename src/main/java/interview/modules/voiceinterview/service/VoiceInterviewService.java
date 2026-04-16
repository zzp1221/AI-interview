package interview.modules.voiceinterview.service;

import interview.common.exception.BusinessException;
import interview.common.exception.ErrorCode;
import interview.common.model.AsyncTaskStatus;
import interview.modules.voiceinterview.dto.CreateSessionRequest;
import interview.modules.voiceinterview.dto.SessionMetaDTO;
import interview.modules.voiceinterview.dto.SessionResponseDTO;
import interview.modules.voiceinterview.dto.VoiceEvaluationDetailDTO;
import interview.modules.voiceinterview.dto.VoiceEvaluationStatusDTO;
import interview.modules.voiceinterview.dto.VoiceInterviewMessageDTO;
import interview.modules.voiceinterview.model.VoiceInterviewMessageEntity;
import interview.modules.voiceinterview.model.VoiceInterviewSessionEntity;
import interview.modules.voiceinterview.model.VoiceInterviewSessionStatus;
import interview.modules.voiceinterview.repository.VoiceInterviewEvaluationRepository;
import interview.modules.voiceinterview.repository.VoiceInterviewMessageRepository;
import interview.modules.voiceinterview.repository.VoiceInterviewSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoiceInterviewService {

    private final VoiceInterviewSessionRepository sessionRepository;
    private final VoiceInterviewMessageRepository messageRepository;
    private final VoiceInterviewEvaluationRepository evaluationRepository;
    private final VoiceInterviewEvaluationService evaluationService;

    @Transactional
    public SessionResponseDTO createSession(Long userId, CreateSessionRequest request) {
        VoiceInterviewSessionEntity session = new VoiceInterviewSessionEntity();
        String skillId = request.skillId() == null || request.skillId().isBlank() ? "java-backend" : request.skillId();
        session.setUserId(userId);
        session.setSkillId(skillId);
        session.setRoleType(request.roleType() == null ? skillId : request.roleType());
        session.setDifficulty(request.difficulty() == null ? "mid" : request.difficulty());
        session.setResumeId(request.resumeId());
        session.setLlmProvider(request.llmProvider());
        session.setPlannedDuration(request.plannedDuration() == null ? 20 : request.plannedDuration());
        session.setStatus(VoiceInterviewSessionStatus.IN_PROGRESS);
        session.setCurrentPhase(determineFirstPhase(request));
        VoiceInterviewSessionEntity saved = sessionRepository.save(session);
        return toSessionResponse(saved);
    }

    public SessionResponseDTO getSessionDTO(Long userId, Long sessionId) {
        return toSessionResponse(getByIdAndUserOrThrow(userId, sessionId));
    }

    public List<SessionMetaDTO> getAllSessions(Long userId, String status) {
        List<VoiceInterviewSessionEntity> sessions;
        if (status != null && !status.isBlank()) {
            VoiceInterviewSessionStatus s = VoiceInterviewSessionStatus.valueOf(status.toUpperCase());
            sessions = sessionRepository.findByUserIdAndStatusOrderByUpdatedAtDesc(userId, s);
        } else {
            sessions = sessionRepository.findByUserIdOrderByUpdatedAtDesc(userId);
        }
        return sessions.stream()
                .map(s -> new SessionMetaDTO(
                        s.getId(),
                        s.getRoleType(),
                        s.getStatus().name(),
                        s.getCurrentPhase().name(),
                        s.getCreatedAt(),
                        s.getUpdatedAt(),
                        s.getActualDuration(),
                        messageRepository.countBySessionId(s.getId()),
                        s.getEvaluateStatus() == null ? null : s.getEvaluateStatus().name(),
                        s.getEvaluateError()
                ))
                .toList();
    }

    public List<VoiceInterviewMessageDTO> getConversationHistoryDTO(Long userId, Long sessionId) {
        getByIdAndUserOrThrow(userId, sessionId);
        return messageRepository.findBySessionIdOrderBySequenceNumAsc(sessionId).stream()
                .map(m -> new VoiceInterviewMessageDTO(
                        m.getId(),
                        m.getSessionId(),
                        m.getMessageType(),
                        m.getPhase(),
                        m.getUserRecognizedText(),
                        m.getAiGeneratedText(),
                        m.getTimestamp(),
                        m.getSequenceNum()
                )).toList();
    }

    @Transactional
    public void saveMessage(Long sessionId, String userText, String aiText) {
        VoiceInterviewSessionEntity session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "会话不存在"));
        VoiceInterviewMessageEntity msg = new VoiceInterviewMessageEntity();
        msg.setSessionId(sessionId);
        msg.setMessageType("DIALOGUE");
        msg.setPhase(session.getCurrentPhase().name());
        msg.setUserRecognizedText(userText);
        msg.setAiGeneratedText(aiText);
        msg.setSequenceNum((int) messageRepository.countBySessionId(sessionId) + 1);
        messageRepository.save(msg);
    }

    @Transactional
    public void endSession(Long userId, Long sessionId) {
        VoiceInterviewSessionEntity session = getByIdAndUserOrThrow(userId, sessionId);
        if (session.getStatus() == VoiceInterviewSessionStatus.COMPLETED) {
            return;
        }
        session.setStatus(VoiceInterviewSessionStatus.COMPLETED);
        session.setCurrentPhase(VoiceInterviewSessionEntity.InterviewPhase.COMPLETED);
        session.setEndTime(LocalDateTime.now());
        session.setActualDuration((int) Duration.between(session.getStartTime(), session.getEndTime()).toSeconds());
        session.setEvaluateStatus(AsyncTaskStatus.PENDING);
        session.setEvaluateError(null);
        sessionRepository.save(session);
        triggerEvaluation(userId, sessionId);
    }

    @Transactional
    public void pauseSession(Long userId, Long sessionId, String reason) {
        VoiceInterviewSessionEntity session = getByIdAndUserOrThrow(userId, sessionId);
        session.setStatus(VoiceInterviewSessionStatus.PAUSED);
        sessionRepository.save(session);
    }

    @Transactional
    public SessionResponseDTO resumeSession(Long userId, Long sessionId) {
        VoiceInterviewSessionEntity session = getByIdAndUserOrThrow(userId, sessionId);
        session.setStatus(VoiceInterviewSessionStatus.IN_PROGRESS);
        sessionRepository.save(session);
        return toSessionResponse(session);
    }

    @Transactional
    public void deleteSession(Long userId, Long sessionId) {
        getByIdAndUserOrThrow(userId, sessionId);
        evaluationRepository.findBySessionId(sessionId).ifPresent(evaluationRepository::delete);
        messageRepository.deleteBySessionId(sessionId);
        sessionRepository.deleteById(sessionId);
    }

    @Transactional
    public void triggerEvaluation(Long userId, Long sessionId) {
        VoiceInterviewSessionEntity session = getByIdAndUserOrThrow(userId, sessionId);
        if (session.getEvaluateStatus() == AsyncTaskStatus.COMPLETED) {
            return;
        }
        try {
            session.setEvaluateStatus(AsyncTaskStatus.PROCESSING);
            session.setEvaluateError(null);
            sessionRepository.save(session);
            evaluationService.generateEvaluation(sessionId);
            session.setEvaluateStatus(AsyncTaskStatus.COMPLETED);
            sessionRepository.save(session);
        } catch (Exception e) {
            session.setEvaluateStatus(AsyncTaskStatus.FAILED);
            session.setEvaluateError(e.getMessage());
            sessionRepository.save(session);
        }
    }

    public VoiceEvaluationStatusDTO getEvaluationStatus(Long userId, Long sessionId) {
        VoiceInterviewSessionEntity session = getByIdAndUserOrThrow(userId, sessionId);
        VoiceEvaluationDetailDTO detail = null;
        if (session.getEvaluateStatus() == AsyncTaskStatus.COMPLETED) {
            detail = evaluationService.getEvaluation(sessionId);
        }
        return new VoiceEvaluationStatusDTO(
                session.getEvaluateStatus() == null ? null : session.getEvaluateStatus().name(),
                session.getEvaluateError(),
                detail
        );
    }

    public VoiceInterviewSessionEntity getById(Long sessionId) {
        return sessionRepository.findById(sessionId).orElse(null);
    }

    private VoiceInterviewSessionEntity getByIdAndUserOrThrow(Long userId, Long sessionId) {
        return sessionRepository.findByIdAndUserId(sessionId, userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "语音面试会话不存在: " + sessionId));
    }

    private SessionResponseDTO toSessionResponse(VoiceInterviewSessionEntity session) {
        return new SessionResponseDTO(
                session.getId(),
                session.getRoleType(),
                session.getCurrentPhase().name(),
                session.getStatus().name(),
                session.getStartTime(),
                session.getPlannedDuration(),
                "/ws/voice-interview/" + session.getId()
        );
    }

    private VoiceInterviewSessionEntity.InterviewPhase determineFirstPhase(CreateSessionRequest request) {
        if (Boolean.TRUE.equals(request.introEnabled())) return VoiceInterviewSessionEntity.InterviewPhase.INTRO;
        if (Boolean.TRUE.equals(request.techEnabled()) || request.techEnabled() == null) return VoiceInterviewSessionEntity.InterviewPhase.TECH;
        if (Boolean.TRUE.equals(request.projectEnabled())) return VoiceInterviewSessionEntity.InterviewPhase.PROJECT;
        if (Boolean.TRUE.equals(request.hrEnabled())) return VoiceInterviewSessionEntity.InterviewPhase.HR;
        return VoiceInterviewSessionEntity.InterviewPhase.TECH;
    }
}

