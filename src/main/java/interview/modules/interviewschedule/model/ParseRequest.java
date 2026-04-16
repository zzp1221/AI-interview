package interview.modules.interviewschedule.model;

import jakarta.validation.constraints.NotBlank;

public record ParseRequest(
        @NotBlank(message = "文本不能为空")
        String rawText,
        String source
) {
}

