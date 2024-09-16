package kitten.diy.api.test;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final TestService testService;

    @GetMapping("/test")
    public void test() {
        testService.test();
    }

    @GetMapping("/health/check")
    public String healthCheck() {
        return "ok";
    }

    @GetMapping("/test/auth/user")
    @Secured(value = "ROLE_USER")
    public String checkUser() {
        return "ROLE_USER access";
    }

    @GetMapping("/test/auth/anonymous")
    @Secured(value = "ROLE_USER")
    public String checkAnonymous() {
        return "ROLE_ANONYMOUS access";
    }
}
