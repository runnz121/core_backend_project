package kitten.diy.api.adapter.in.web;

import kitten.core.corecommon.annotation.Description;
import kitten.core.corecommon.security.jwt.AccessAccount;
import kitten.core.corecommon.security.jwt.CurrentAccount;
import kitten.diy.api.adapter.in.web.request.MoruPartsRequest;
import kitten.diy.api.adapter.in.web.request.TagLikeSearchRequest;
import kitten.diy.api.adapter.out.model.PartDetail;
import kitten.diy.api.application.port.in.command.PartsCommandUseCase;
import kitten.diy.api.application.port.in.command.command.ItemSearchCommand;
import kitten.diy.api.application.port.in.command.command.PartsSearchCommand;
import kitten.diy.api.application.port.in.query.ItemQueryUseCase;
import kitten.diy.api.application.port.in.query.PartsQueryUseCase;
import kitten.diy.api.application.port.in.query.data.ItemThemeData;
import kitten.diy.api.application.port.in.query.data.PartsThemeData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
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
    @Secured(value = "ROLE_USER")
    @PostMapping("/moru/parts")
    public void registerMoruParts(@RequestBody MoruPartsRequest request,
                                  @AccessAccount CurrentAccount account) {
        partsCommandUseCase.registerMoruParts(request.toCommand(account.getUserEmail()));
    }

    @Description("모루 아이템 정보 조회")
    @GetMapping("/moru")
    @Secured(value = {"ROLE_USER", "ROLE_ANONYMOUS"})
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

    @Description("모루 파츠 상세조회 (부모키로 조회)")
    @GetMapping("/moru/parts/{partsParentKey}")
    public PartDetail getMoruPartsDetail(@PathVariable Long partsParentKey) {
        return partsQueryUseCase.getPartDetail(partsParentKey);
    }

    @Description("모루 파츠 정보 리스트 조회 (관리자만 diy kitten)")
    @Secured(value = "ROLE_USER")
    @GetMapping("/moru/parts/all")
    public List<PartDetail> getMoruPartDetails() {
        return partsQueryUseCase.getAllPartsDetails();
    }

    @Description("모루 파츠 수정 (관리자만 diy kitten)")
    @Secured(value = "ROLE_USER")
    @PutMapping("/moru/parts")
    public void modifyMoruParts(@RequestBody MoruPartsRequest request,
                                @AccessAccount CurrentAccount account) {
        partsCommandUseCase.modifyMoruParts(request.toCommand(account.getUserEmail()));
    }

    @Description("모루 파츠 삭제 (관리자만 diy kitten)")
    @Secured(value = "ROLE_USER")
    @DeleteMapping("/moru/parts")
    public void deleteMoruPart(@RequestParam Long parentPartsKey) {
        partsCommandUseCase.deleteMoruParts(parentPartsKey);
    }
}
