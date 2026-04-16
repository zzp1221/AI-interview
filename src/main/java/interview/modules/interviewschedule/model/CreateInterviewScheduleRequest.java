package interview.modules.interviewschedule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateInterviewScheduleRequest(
        @NotBlank(message = "公司名称不能为空")
        String companyName,
        @NotBlank(message = "岗位不能为空")
        String position,
        @NotNull(message = "面试时间不能为空")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm[:ss]")
        LocalDateTime interviewTime,
        String interviewType,
        String meetingLink,
        Integer roundNumber,
        String interviewer,
        String notes
) {
}

