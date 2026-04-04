package interview.modules.user;

import interview.common.result.Result;
import interview.modules.user.model.UserAuthDTO.AuthResponse;
import interview.modules.user.model.UserAuthDTO.LoginRequest;
import interview.modules.user.model.UserAuthDTO.RegisterRequest;
import interview.modules.user.service.UserAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/ai/interview/login")
    public Result<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return Result.success(userAuthService.login(request));
    }

    @PostMapping("/ai/interview/registant")
    public Result<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return Result.success(userAuthService.register(request));
    }

}
