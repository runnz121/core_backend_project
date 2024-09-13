package kitten.diy.api.test;

import lombok.RequiredArgsConstructor;
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
}
