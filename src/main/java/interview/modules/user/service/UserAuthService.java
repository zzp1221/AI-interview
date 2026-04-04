package interview.modules.user.service;

import interview.common.exception.BusinessException;
import interview.common.exception.ErrorCode;
import interview.infrastructure.mapper.KnowledgeBaseMapper;
import interview.modules.knowledgebase.model.KnowledgeBaseListItemDTO;
import interview.modules.knowledgebase.repository.KnowledgeBaseRepository;
import interview.modules.user.model.UserAuthDTO.AuthResponse;
import interview.modules.user.model.UserAuthDTO.LoginRequest;
import interview.modules.user.model.UserAuthDTO.RegisterRequest;
import interview.modules.user.model.UserAuthDTO.UserInfoDTO;
import interview.modules.user.model.UserEntity;
import interview.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAuthService {

    private static final String HASH_SPLITTER = ":";
    private static final int SALT_BYTES = 16;

    private final UserRepository userRepository;
    private final KnowledgeBaseRepository knowledgeBaseRepository;
    private final KnowledgeBaseMapper knowledgeBaseMapper;
    private final SecureRandom secureRandom = new SecureRandom();

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        String username = normalizeUsername(request.username());
        String password = request.password();
        if (userRepository.existsByUsername(username)) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "用户名已存在");
        }

        UserEntity entity = new UserEntity();
        entity.setUsername(username);
        entity.setPasswordHash(hashPassword(password));
        UserEntity saved = userRepository.save(entity);
        log.info("用户注册成功: userId={}, username={}", saved.getId(), saved.getUsername());
        return buildAuthResponse(saved);
    }

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        String username = normalizeUsername(request.username());
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(ErrorCode.UNAUTHORIZED, "用户名或密码错误"));
        if (!verifyPassword(request.password(), user.getPasswordHash())) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED, "用户名或密码错误");
        }
        log.info("用户登录成功: userId={}, username={}", user.getId(), user.getUsername());
        return buildAuthResponse(user);
    }

    private AuthResponse buildAuthResponse(UserEntity user) {
        List<KnowledgeBaseListItemDTO> knowledgeBases = knowledgeBaseMapper.toListItemDTOList(
                knowledgeBaseRepository.findByUserIdOrderByUploadedAtDesc(user.getId())
        );
        return new AuthResponse(
                new UserInfoDTO(user.getId(), user.getUsername()),
                knowledgeBases
        );
    }

    private String hashPassword(String rawPassword) {
        byte[] salt = new byte[SALT_BYTES];
        secureRandom.nextBytes(salt);
        byte[] digest = digest(salt, rawPassword);
        return Base64.getEncoder().encodeToString(salt)
                + HASH_SPLITTER
                + Base64.getEncoder().encodeToString(digest);
    }

    private boolean verifyPassword(String rawPassword, String storedHash) {
        if (storedHash == null || storedHash.isBlank()) {
            return false;
        }
        String[] parts = storedHash.split(HASH_SPLITTER);
        if (parts.length != 2) {
            return false;
        }
        try {
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] expectedDigest = Base64.getDecoder().decode(parts[1]);
            byte[] actualDigest = digest(salt, rawPassword);
            return MessageDigest.isEqual(actualDigest, expectedDigest);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private byte[] digest(byte[] salt, String rawPassword) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(salt);
            messageDigest.update(rawPassword.getBytes(StandardCharsets.UTF_8));
            return messageDigest.digest();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_ERROR, "密码处理失败");
        }
    }

    private String normalizeUsername(String username) {
        String normalized = Objects.requireNonNullElse(username, "").trim();
        if (normalized.isEmpty()) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "用户名不能为空");
        }
        return normalized;
    }
}
