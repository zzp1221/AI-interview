package interview.common.security;

import interview.common.exception.BusinessException;
import interview.common.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Queue;

@Component
public class CurrentUserProvider {

    private static final String USER_ID_ATTRIBUTE = "userId";
    private static final String USER_ID_HEADER = "X-User-Id";

    public Long getRequiredUserId() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED, "未登录或登录已过期");
        }
        HttpServletRequest request = attributes.getRequest();
        Object attrUserId = request.getAttribute(USER_ID_ATTRIBUTE);
        if (attrUserId != null) {
            return parseUserId(attrUserId.toString());
        }
        String headerUserId = request.getHeader(USER_ID_HEADER);
        if (headerUserId != null && !headerUserId.isBlank()) {
            return parseUserId(headerUserId);
        }
        
        throw new BusinessException(ErrorCode.UNAUTHORIZED, "未登录或登录已过期");
    }

    private Long parseUserId(String value) {
        try {
            Long userId = Long.parseLong(value.trim());
            if (userId <= 0) {
                throw new BusinessException(ErrorCode.UNAUTHORIZED, "无效用户身份");
            }
            return userId;
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED, "无效用户身份");
        }
    }
}
