package interview.modules.interviewschedule;

import interview.common.result.Result;
import interview.common.security.CurrentUserProvider;
import interview.modules.interviewschedule.model.CreateInterviewScheduleRequest;
import interview.modules.interviewschedule.model.InterviewScheduleDTO;
import interview.modules.interviewschedule.model.ParseRequest;
import interview.modules.interviewschedule.model.ParseResponse;
import interview.modules.interviewschedule.model.UpdateInterviewStatusRequest;
import interview.modules.interviewschedule.service.InterviewParseService;
import interview.modules.interviewschedule.service.InterviewScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class InterviewScheduleController {

    private final InterviewScheduleService scheduleService;
    private final InterviewParseService parseService;
    private final CurrentUserProvider currentUserProvider;

    @GetMapping("/api/interview-schedules")
    public Result<List<InterviewScheduleDTO>> list(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    ) {
        Long userId = currentUserProvider.getRequiredUserId();
        return Result.success(scheduleService.getAll(userId, status, start, end));
    }

    @GetMapping("/api/interview-schedules/{id}")
    public Result<InterviewScheduleDTO> detail(@PathVariable Long id) {
        Long userId = currentUserProvider.getRequiredUserId();
        return Result.success(scheduleService.getById(userId, id));
    }

    @PostMapping("/api/interview-schedules")
    public Result<InterviewScheduleDTO> create(@Valid @RequestBody CreateInterviewScheduleRequest request) {
        Long userId = currentUserProvider.getRequiredUserId();
        return Result.success(scheduleService.create(userId, request));
    }

    @PutMapping("/api/interview-schedules/{id}")
    public Result<InterviewScheduleDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody CreateInterviewScheduleRequest request
    ) {
        Long userId = currentUserProvider.getRequiredUserId();
        return Result.success(scheduleService.update(userId, id, request));
    }

    @DeleteMapping("/api/interview-schedules/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        Long userId = currentUserProvider.getRequiredUserId();
        scheduleService.delete(userId, id);
        return Result.success();
    }

    @PatchMapping("/api/interview-schedules/{id}/status")
    public Result<InterviewScheduleDTO> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateInterviewStatusRequest request
    ) {
        Long userId = currentUserProvider.getRequiredUserId();
        return Result.success(scheduleService.updateStatus(userId, id, request.status()));
    }

    @PostMapping("/api/interview-schedules/parse")
    public Result<ParseResponse> parse(@Valid @RequestBody ParseRequest request) {
        return Result.success(parseService.parse(request.rawText(), request.source()));
    }
}

