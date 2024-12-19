package kitten.diy.api.adapter.in.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kitten.core.corecommon.security.jwt.AccessAccount;
import kitten.core.corecommon.security.jwt.CurrentAccount;
import kitten.diy.api.application.port.in.command.AuthCommandUseCase;
import kitten.diy.api.application.port.in.command.command.LogoutCommand;
import kitten.diy.api.application.port.in.query.data.LogoutResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthCommandUseCase authCommandUseCase;

    @Secured(value = "ROLE_USER")
    @PutMapping("/logout")
    public LogoutResultData logOut(@AccessAccount CurrentAccount account,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        return authCommandUseCase.logOut(new LogoutCommand(request, response));
    }
}
