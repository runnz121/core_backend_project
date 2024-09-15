package kitten.diy.api.adapter.in.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kitten.diy.api.adapter.in.web.request.JoinRequest;
import kitten.diy.api.adapter.in.web.request.ValidateValueRequest;
import kitten.diy.api.application.port.in.command.JoinCommandUseCase;
import kitten.diy.api.application.port.in.command.command.ValidateValueCommand;
import kitten.diy.api.application.port.in.query.JoinQueryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/join")
@RequiredArgsConstructor
public class JoinController {

    private final JoinCommandUseCase joinCommandUseCase;
    private final JoinQueryUseCase joinQueryUseCase;

    @PostMapping
    public void join(@RequestBody JoinRequest joinRequest,
                     HttpServletRequest request,
                     HttpServletResponse response) {
        joinCommandUseCase.joinUser(joinRequest.toCommand(request, response));
    }

    @PostMapping("/check/nick-name")
    public void checkNickName(@RequestBody ValidateValueRequest request) {
        joinQueryUseCase.validateNickName(new ValidateValueCommand(request.value()));
    }
}
