package kitten.diy.api.adapter.in.web;

import kitten.core.corecommon.security.jwt.AccessAccount;
import kitten.core.corecommon.security.jwt.CurrentAccount;
import kitten.diy.api.adapter.in.web.request.MoruPartsRequest;
import kitten.diy.api.application.port.in.command.ItemCommandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemTemplateController {

    private final ItemCommandUseCase itemCommandUseCase;

    @Secured(value = "ROLE_USER")
    @PostMapping("/moru/parts")
    public void registerMoruParts(@RequestBody MoruPartsRequest request,
                                  @AccessAccount CurrentAccount account) {

    }
}
