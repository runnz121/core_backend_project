package kitten.diy.api.adapter.in.web;

import kitten.core.corecommon.annotation.Description;
import kitten.core.corecommon.security.jwt.AccessAccount;
import kitten.core.corecommon.security.jwt.CurrentAccount;
import kitten.diy.api.adapter.in.web.request.MoruPartsRequest;
import kitten.diy.api.adapter.in.web.request.TagLikeSearchRequest;
import kitten.diy.api.application.port.in.command.PartsCommandUseCase;
import kitten.diy.api.application.port.in.command.command.ItemSearchCommand;
import kitten.diy.api.application.port.in.command.command.PartsSearchCommand;
import kitten.diy.api.application.port.in.query.ItemQueryUseCase;
import kitten.diy.api.application.port.in.query.PartsQueryUseCase;
import kitten.diy.api.application.port.in.query.data.ItemThemeData;
import kitten.diy.api.application.port.in.query.data.PartsThemeData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemTemplateController {

    private final ItemQueryUseCase itemQueryUseCase;
    private final PartsCommandUseCase partsCommandUseCase;
    private final PartsQueryUseCase partsQueryUseCase;

    @Description("모루 파츠 정보 저장")
//    @Secured(value = "ROLE_USER")
    @PostMapping("/moru/parts")
    public void registerMoruParts(@RequestBody MoruPartsRequest request,
                                  @AccessAccount CurrentAccount account) {
        account = CurrentAccount.defaultValue();
        partsCommandUseCase.registerMoruParts(request.toCommand(account.getUserEmail()));
    }

    @Description("모루 아이템 정보 조회")
    @GetMapping("/moru")
    public ItemThemeData getItem(@AccessAccount CurrentAccount account,
                                 @RequestParam("item") String item,
                                 @RequestParam("theme") String theme) {
        return itemQueryUseCase.getThemeItemData(ItemSearchCommand.of(item, theme));
    }

    @Description("모루 파츠 갖고오기")
//    @Secured(value = "ROLE_USER")
    @GetMapping("/moru/parts")
    public List<PartsThemeData> getParts(@AccessAccount CurrentAccount account,
                                         @RequestParam("item") String item,
                                         @RequestParam("theme") String theme) {
        account = CurrentAccount.defaultValue();
        return partsQueryUseCase.getPartsByTheme(PartsSearchCommand.of(item, theme));
    }

    @Description("모루 파츠 비슷한 태그 정보 조회")
    @PostMapping("/parts/like/tags")
    public List<String> getPartsLikeTags(@RequestBody TagLikeSearchRequest request) {
        return itemQueryUseCase.getLikePartsTags(request.toCommand());
    }
}
