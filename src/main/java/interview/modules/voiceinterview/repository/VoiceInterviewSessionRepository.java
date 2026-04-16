package interview.modules.voiceinterview.repository;

import interview.modules.voiceinterview.model.VoiceInterviewSessionEntity;
import interview.modules.voiceinterview.model.VoiceInterviewSessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoiceInterviewSessionRepository extends JpaRepository<VoiceInterviewSessionEntity, Long> {
    List<VoiceInterviewSessionEntity> findByUserIdOrderByUpdatedAtDesc(Long userId);

    List<VoiceInterviewSessionEntity> findByUserIdAndStatusOrderByUpdatedAtDesc(Long userId, VoiceInterviewSessionStatus status);

    Optional<VoiceInterviewSessionEntity> findByIdAndUserId(Long id, Long userId);
}

