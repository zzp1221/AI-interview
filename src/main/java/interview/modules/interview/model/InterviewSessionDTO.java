package interview.modules.interview.model;

import java.util.List;

/**
 * 面试会话DTO
 * 在InterviewSessionService具体实现
 *
 */
public record InterviewSessionDTO(
        String sessionId,
        String resumeText,
        int totalQuestions,
        int currentQuestionIndex,
        List<InterviewQuestionDTO> questions,
        SessionStatus status
) {
    /**
     * CREATED->IN_PROGRESS:只转换一次，记录面试开始时间
     * IN_PROGRESS->IN_PROGRESS: 允许用户跳题、修改答案，每题独立提交
     * IN_PROGRESS->COMPLETED: 用户主动交卷或答完题目
     * COMPLETED->EVALUATED: 由异步任务触发，不可逆
     */
    public enum SessionStatus {
        CREATED,      // 会话已创建
        IN_PROGRESS,  // 面试进行中
        COMPLETED,    // 面试已完成
        EVALUATED     // 已生成评估报告
    }
}
