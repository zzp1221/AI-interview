package interview.modules.voiceinterview.repository;

import interview.modules.voiceinterview.model.VoiceInterviewEvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoiceInterviewEvaluationRepository extends JpaRepository<VoiceInterviewEvaluationEntity, Long> {
    Optional<VoiceInterviewEvaluationEntity> findBySessionId(Long sessionId);
}

