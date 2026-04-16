package interview.modules.voiceinterview.repository;

import interview.modules.voiceinterview.model.VoiceInterviewMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoiceInterviewMessageRepository extends JpaRepository<VoiceInterviewMessageEntity, Long> {
    List<VoiceInterviewMessageEntity> findBySessionIdOrderBySequenceNumAsc(Long sessionId);

    long countBySessionId(Long sessionId);

    void deleteBySessionId(Long sessionId);
}

