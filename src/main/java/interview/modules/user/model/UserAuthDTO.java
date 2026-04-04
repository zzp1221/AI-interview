package interview.modules.user.model;

import interview.modules.knowledgebase.model.KnowledgeBaseListItemDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class UserAuthDTO {

    public record LoginRequest(
            @NotBlank(message = "用户名不能为空")
            @Size(max = 64, message = "用户名长度不能超过64个字符")
            String username,
            @NotBlank(message = "密码不能为空")
            @Size(max = 128, message = "密码长度不能超过128个字符")
            String password
    ) {}

    public record RegisterRequest(
            @NotBlank(message = "用户名不能为空")
            @Size(min = 3, max = 64, message = "用户名长度需在3-64个字符之间")
            String username,
            @NotBlank(message = "密码不能为空")
            @Size(min = 6, max = 128, message = "密码长度需在6-128个字符之间")
            String password
    ) {}

    public record UserInfoDTO(
            Long id,
            String username
    ) {}

    public record AuthResponse(
            UserInfoDTO user,
            List<KnowledgeBaseListItemDTO> knowledgeBases
    ) {}
}
