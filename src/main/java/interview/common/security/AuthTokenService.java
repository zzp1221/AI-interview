package interview.common.security;

import interview.common.exception.BusinessException;
import interview.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

@Service
public class AuthTokenService {

    private static final String HMAC_ALGORITHM = "HmacSHA256";

    private final String secret;
    private final long ttlMillis;

    public AuthTokenService(
            @Value("${app.auth.token-secret:ai-interview-token-secret-change-me}") String secret,
            @Value("${app.auth.token-ttl-hours:24}") long tokenTtlHours
    ) {
        this.secret = secret;
        this.ttlMillis = tokenTtlHours * 60L * 60L * 1000L;
    }

    public String generateToken(Long userId) {
        long expiresAt = System.currentTimeMillis() + ttlMillis;
        String payload = userId + ":" + expiresAt;
        String encodedPayload = Base64.getUrlEncoder().withoutPadding()
                .encodeToString(payload.getBytes(StandardCharsets.UTF_8));
        String signature = sign(encodedPayload);
        return encodedPayload + "." + signature;
    }

    public Long parseAndValidateUserId(String token) {
        if (token == null || token.isBlank()) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED, "未登录或登录已过期");
        }
        String[] parts = token.trim().split("\\.");
        if (parts.length != 2) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED, "无效登录凭证");
        }
        String encodedPayload = parts[0];
        String signature = parts[1];
        String expected = sign(encodedPayload);
        if (!MessageDigest.isEqual(signature.getBytes(StandardCharsets.UTF_8), expected.getBytes(StandardCharsets.UTF_8))) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED, "无效登录凭证");
        }
        String payload;
        try {
            payload = new String(Base64.getUrlDecoder().decode(encodedPayload), StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED, "无效登录凭证");
        }
        String[] values = payload.split(":");
        if (values.length != 2) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED, "无效登录凭证");
        }
        try {
            long userId = Long.parseLong(values[0]);
            long expiresAt = Long.parseLong(values[1]);
            if (userId <= 0 || expiresAt < System.currentTimeMillis()) {
                throw new BusinessException(ErrorCode.UNAUTHORIZED, "登录已过期，请重新登录");
            }
            return userId;
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED, "无效登录凭证");
        }
    }

    private String sign(String data) {
        try {
            Mac mac = Mac.getInstance(HMAC_ALGORITHM);
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HMAC_ALGORITHM));
            byte[] signature = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(signature);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_ERROR, "签名计算失败");
        }
    }
}
