package interview.modules.interviewschedule.model;

public record ParseResponse(
        Boolean success,
        CreateInterviewScheduleRequest data,
        Double confidence,
        String parseMethod,
        String log
) {
}

