package interview.modules.voiceinterview.controller;

import interview.common.result.Result;
import interview.common.security.CurrentUserProvider;
import interview.modules.voiceinterview.dto.CreateSessionRequest;
import interview.modules.voiceinterview.dto.SessionMetaDTO;
import interview.modules.voiceinterview.dto.SessionResponseDTO;
import interview.modules.voiceinterview.dto.VoiceEvaluationStatusDTO;
import interview.modules.voiceinterview.dto.VoiceInterviewMessageDTO;
import interview.modules.voiceinterview.service.VoiceInterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/voice-interview")
@RequiredArgsConstructor
public class VoiceInterviewController {

    private final VoiceInterviewService voiceInterviewService;
    private final CurrentUserProvider currentUserProvider;

    @PostMapping("/sessions")
    public Result<SessionResponseDTO> createSession(@RequestBody CreateSessionRequest request) {
        Long userId = currentUserProvider.getRequiredUserId();
        return Result.success(voiceInterviewService.createSession(userId, request));
    }

    @GetMapping("/sessions/{sessionId}")
    public Result<SessionResponseDTO> getSession(@PathVariable Long sessionId) {
        Long userId = currentUserProvider.getRequiredUserId();
        return Result.success(voiceInterviewService.getSessionDTO(userId, sessionId));
    }

    @GetMapping("/sessions")
    public Result<List<SessionMetaDTO>> getAllSessions(@RequestParam(required = false) String status) {
        Long userId = currentUserProvider.getRequiredUserId();
        return Result.success(voiceInterviewService.getAllSessions(userId, status));
    }

    @PostMapping("/sessions/{sessionId}/end")
    public Result<Void> endSession(@PathVariable Long sessionId) {
        Long userId = currentUserProvider.getRequiredUserId();
        voiceInterviewService.endSession(userId, sessionId);
        return Result.success();
    }

    @PutMapping("/sessions/{sessionId}/pause")
    public Result<Void> pauseSession(@PathVariable Long sessionId, @RequestBody(required = false) Map<String, String> body) {
        Long userId = currentUserProvider.getRequiredUserId();
        String reason = body == null ? "user_initiated" : body.getOrDefault("reason", "user_initiated");
        voiceInterviewService.pauseSession(userId, sessionId, reason);
        return Result.success();
    }

    @PutMapping("/sessions/{sessionId}/resume")
    public Result<SessionResponseDTO> resumeSession(@PathVariable Long sessionId) {
        Long userId = currentUserProvider.getRequiredUserId();
        return Result.success(voiceInterviewService.resumeSession(userId, sessionId));
    }

    @DeleteMapping("/sessions/{sessionId}")
    public Result<Void> deleteSession(@PathVariable Long sessionId) {
        Long userId = currentUserProvider.getRequiredUserId();
        voiceInterviewService.deleteSession(userId, sessionId);
        return Result.success();
    }

    @GetMapping("/sessions/{sessionId}/messages")
    public Result<List<VoiceInterviewMessageDTO>> getMessages(@PathVariable Long sessionId) {
        Long userId = currentUserProvider.getRequiredUserId();
        return Result.success(voiceInterviewService.getConversationHistoryDTO(userId, sessionId));
    }

    @GetMapping("/sessions/{sessionId}/evaluation")
    public Result<VoiceEvaluationStatusDTO> getEvaluation(@PathVariable Long sessionId) {
        Long userId = currentUserProvider.getRequiredUserId();
        return Result.success(voiceInterviewService.getEvaluationStatus(userId, sessionId));
    }

    @PostMapping("/sessions/{sessionId}/evaluation")
    public Result<VoiceEvaluationStatusDTO> generateEvaluation(@PathVariable Long sessionId) {
        Long userId = currentUserProvider.getRequiredUserId();
        voiceInterviewService.triggerEvaluation(userId, sessionId);
        return Result.success(voiceInterviewService.getEvaluationStatus(userId, sessionId));
    }
}

