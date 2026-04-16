package interview.modules.interviewschedule.model;

import jakarta.validation.constraints.NotNull;

public record UpdateInterviewStatusRequest(
        @NotNull(message = "状态不能为空")
        InterviewStatus status
) {
}

