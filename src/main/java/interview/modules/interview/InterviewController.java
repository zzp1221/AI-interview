package interview.modules.interview;

import interview.common.result.Result;
import interview.modules.interview.model.CreateInterviewRequest;
import interview.modules.interview.model.InterviewDetailDTO;
import interview.modules.interview.model.InterviewReportDTO;
import interview.modules.interview.model.InterviewSessionDTO;
import interview.modules.interview.model.SubmitAnswerResponse;
import interview.modules.interview.service.InterviewHistoryService;
import interview.modules.interview.service.InterviewPersistenceService;
import interview.modules.interview.service.InterviewSessionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Validated
@RestController
@RequiredArgsConstructor
public class InterviewController {

    private final InterviewSessionService interviewSessionService;
    private final InterviewHistoryService interviewHistoryService;
    private final InterviewPersistenceService interviewPersistenceService;

    @PostMapping("/api/interview/sessions")
    public Result<InterviewSessionDTO> createSession(@Valid @RequestBody CreateInterviewRequest request) {
        return Result.success(interviewSessionService.createSession(request));
    }

    @GetMapping("/api/interview/sessions/{sessionId}")
    public Result<InterviewSessionDTO> getSession(@PathVariable String sessionId) {
        return Result.success(interviewSessionService.getSession(sessionId));
    }

    @GetMapping("/api/interview/sessions/{sessionId}/unfinished")
    public Result<InterviewSessionDTO> getSessionAsUnfinished(@PathVariable String sessionId) {
        return Result.success(interviewSessionService.getSession(sessionId));
    }

    @GetMapping("/api/interview/sessions/unfinished/{resumeId}")
    public Result<InterviewSessionDTO> findUnfinishedSession(@PathVariable Long resumeId) {
        return Result.success(interviewSessionService.findUnfinishedSession(resumeId).orElse(null));
    }

    @GetMapping("/api/interview/sessions/{sessionId}/question")
    public Result<Map<String, Object>> getCurrentQuestion(@PathVariable String sessionId) {
        return Result.success(interviewSessionService.getCurrentQuestionResponse(sessionId));
    }

    @PostMapping("/api/interview/sessions/{sessionId}/answers")
    public Result<SubmitAnswerResponse> submitAnswer(
            @PathVariable String sessionId,
            @Valid @RequestBody AnswerPayload payload
    ) {
        return Result.success(interviewSessionService.submitAnswer(payload.toSubmitRequest(sessionId)));
    }

    @PutMapping("/api/interview/sessions/{sessionId}/answers")
    public Result<Void> saveAnswer(
            @PathVariable String sessionId,
            @Valid @RequestBody AnswerPayload payload
    ) {
        interviewSessionService.saveAnswer(payload.toSubmitRequest(sessionId));
        return Result.success();
    }

    @PostMapping("/api/interview/sessions/{sessionId}/complete")
    public Result<Void> completeInterview(@PathVariable String sessionId) {
        interviewSessionService.completeInterview(sessionId);
        return Result.success();
    }

    @GetMapping("/api/interview/sessions/{sessionId}/report")
    public Result<InterviewReportDTO> getReport(@PathVariable String sessionId) {
        return Result.success(interviewSessionService.generateReport(sessionId));
    }

    @GetMapping("/api/interview/sessions/{sessionId}/details")
    public Result<InterviewDetailDTO> getInterviewDetail(@PathVariable String sessionId) {
        return Result.success(interviewHistoryService.getInterviewDetail(sessionId));
    }

    @GetMapping("/api/interview/sessions/{sessionId}/export")
    public ResponseEntity<byte[]> exportInterviewPdf(@PathVariable String sessionId) {
        byte[] pdfBytes = interviewHistoryService.exportInterviewPdf(sessionId);
        String filename = "面试报告_" + sessionId + ".pdf";
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                        .filename(filename, StandardCharsets.UTF_8)
                        .build()
                        .toString())
                .body(pdfBytes);
    }

    @DeleteMapping("/api/interview/sessions/{sessionId}")
    public Result<Void> deleteInterview(@PathVariable String sessionId) {
        interviewPersistenceService.deleteSessionBySessionId(sessionId);
        return Result.success();
    }

    public record AnswerPayload(
            @NotNull(message = "问题索引不能为空")
            @Min(value = 0, message = "问题索引无效")
            Integer questionIndex,
            @NotBlank(message = "答案不能为空")
            String answer
    ) {
        interview.modules.interview.model.SubmitAnswerRequest toSubmitRequest(String sessionId) {
            return new interview.modules.interview.model.SubmitAnswerRequest(sessionId, questionIndex, answer);
        }
    }
}
