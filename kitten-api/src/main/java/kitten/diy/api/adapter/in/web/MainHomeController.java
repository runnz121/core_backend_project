package kitten.diy.api.adapter.in.web;

import kitten.core.coredomain.page.PageableData;
import kitten.diy.api.adapter.in.web.request.HomeSearchRequest;
import kitten.diy.api.application.port.in.MainHomeQueryUseCase;
import kitten.diy.api.application.port.in.query.MainHomeInfoData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class MainHomeController {

    private final MainHomeQueryUseCase mainHomeQueryUseCase;

    @PostMapping("/list")
    public PageableData<List<MainHomeInfoData>> getHomeMainList(@RequestBody HomeSearchRequest request) {
        return mainHomeQueryUseCase.getMainHomeInfos(request.toCommand());
    }
}
