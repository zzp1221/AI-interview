package interview.modules.resume;

import interview.common.exception.BusinessException;
import interview.common.exception.ErrorCode;
import interview.common.result.Result;
import interview.modules.resume.model.ResumeEntity;
import interview.modules.resume.model.ResumeListItemDTO;
import interview.modules.resume.service.ResumeDeleteService;
import interview.modules.resume.service.ResumeHistoryService;
import interview.modules.resume.service.ResumePersistenceService;
import interview.modules.resume.service.ResumeUploadService;
import interview.modules.resume.listener.AnalyzeStreamProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeUploadService resumeUploadService;
    private final ResumeHistoryService resumeHistoryService;
    private final ResumeDeleteService resumeDeleteService;
    private final ResumePersistenceService resumePersistenceService;
    private final AnalyzeStreamProducer analyzeStreamProducer;

    @PostMapping(value = "/api/resumes/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<Map<String, Object>> uploadAndAnalyze(
            @RequestParam("file") MultipartFile file) {
        Map<String, Object> result = resumeUploadService.uploadAndAnalyze(file);
        boolean isDuplicate = (Boolean) result.get("duplicate");
        if (isDuplicate) {
            return Result.success("检测到相同简历，返回历史分析结果", result);
        }
        return Result.success(result);
    }

    @GetMapping("/api/resumes")
    public Result<List<ResumeListItemDTO>> getResumes() {
        return Result.success(resumeHistoryService.getAllResumes());
    }

    @GetMapping("/api/resumes/{id}/detail")
    public Result<?> getResumeDetail(@PathVariable Long id) {
        return Result.success(resumeHistoryService.getResumeDetail(id));
    }

    @DeleteMapping("/api/resumes/{id}")
    public Result<Void> deleteResume(@PathVariable Long id) {
        resumeDeleteService.deleteResume(id);
        return Result.success();
    }

    @GetMapping("/api/resumes/statistics")
    public Result<Map<String, Object>> getStatistics() {
        List<ResumeListItemDTO> resumes = resumeHistoryService.getAllResumes();
        int totalInterviewCount = resumes.stream()
                .map(ResumeListItemDTO::interviewCount)
                .filter(count -> count != null)
                .mapToInt(Integer::intValue)
                .sum();
        int totalAccessCount = resumes.stream()
                .map(ResumeListItemDTO::accessCount)
                .filter(count -> count != null)
                .mapToInt(Integer::intValue)
                .sum();

        Map<String, Object> stats = new LinkedHashMap<>();
        stats.put("totalCount", resumes.size());
        stats.put("totalInterviewCount", totalInterviewCount);
        stats.put("totalAccessCount", totalAccessCount);
        return Result.success(stats);
    }

    @PostMapping("/api/resumes/{id}/reanalyze")
    public Result<Void> reanalyze(@PathVariable Long id) {
        ResumeEntity resume = resumePersistenceService.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESUME_NOT_FOUND));
        analyzeStreamProducer.sendAnalyzeTask(resume.getId(), resume.getResumeText());
        return Result.success();
    }

    @GetMapping("/api/resumes/{id}/export")
    public ResponseEntity<byte[]> exportAnalysisPdf(@PathVariable Long id) {
        ResumeHistoryService.ExportResult result = resumeHistoryService.exportAnalysisPdf(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                        .filename(result.filename(), StandardCharsets.UTF_8)
                        .build()
                        .toString())
                .body(result.pdfBytes());
    }
}
