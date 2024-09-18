package kitten.diy.api.adapter.in.web;

import kitten.core.corecommon.security.jwt.AccessAccount;
import kitten.core.corecommon.security.jwt.CurrentAccount;
import kitten.diy.api.adapter.in.web.request.MoruPartsRequest;
import kitten.diy.api.application.port.in.command.PartsCommandUseCase;
import kitten.diy.api.application.port.in.command.command.PartsSearchCommand;
import kitten.diy.api.application.port.in.query.ItemQueryUseCase;
import kitten.diy.api.application.port.in.query.data.PartsThemeData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemTemplateController {

    private final PartsCommandUseCase partsCommandUseCase;
    private final ItemQueryUseCase itemQueryUseCase;

//    @Secured(value = "ROLE_USER")
    @PostMapping("/moru/parts")
    public void registerMoruParts(@RequestBody MoruPartsRequest request,
                                  @AccessAccount CurrentAccount account) {
        partsCommandUseCase.registerMoruParts(request.toCommand(account.getUserEmail()));
    }

//    @Secured(value = "ROLE_USER")
    @GetMapping("/moru/parts")
    public List<PartsThemeData> getParts(@AccessAccount CurrentAccount account,
                                         @RequestParam("item") String item,
                                         @RequestParam("theme") String theme) {
        return itemQueryUseCase.getPartsByTheme(PartsSearchCommand.of(item, theme));
    }
}
