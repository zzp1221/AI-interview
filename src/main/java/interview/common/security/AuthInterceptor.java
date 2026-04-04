package interview.common.security;

import interview.common.exception.BusinessException;
import interview.common.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final String USER_ID_ATTRIBUTE = "userId";
    private static final String USER_ID_HEADER = "X-User-Id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        String userIdHeader = request.getHeader(USER_ID_HEADER);
        if (userIdHeader == null || userIdHeader.isBlank()) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED, "未登录或登录已过期");
        }

        try {
            long userId = Long.parseLong(userIdHeader.trim());
            if (userId <= 0) {
                throw new BusinessException(ErrorCode.UNAUTHORIZED, "无效用户身份");
            }
            request.setAttribute(USER_ID_ATTRIBUTE, userId);
            return true;
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED, "无效用户身份");
        }
    }
}
