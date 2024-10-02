package kitten.diy.api.adapter.in.web;

import kitten.core.corecommon.annotation.Description;
import kitten.core.corecommon.security.jwt.AccessAccount;
import kitten.core.corecommon.security.jwt.CurrentAccount;
import kitten.core.coredomain.page.PageableData;
import kitten.diy.api.adapter.in.web.request.AvatarRequest;
import kitten.diy.api.adapter.in.web.request.MyArtsSearchRequest;
import kitten.diy.api.application.port.in.command.ItemCommandUseCase;
import kitten.diy.api.application.port.in.command.MyPageCommandUseCase;
import kitten.diy.api.application.port.in.query.MyPageQueryUseCase;
import kitten.diy.api.application.port.in.query.data.MyPageArtData;
import kitten.diy.api.application.port.in.query.data.MyPageData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final ItemCommandUseCase itemCommandUseCase;
    private final MyPageQueryUseCase myPageQueryUseCase;
    private final MyPageCommandUseCase myPageCommandUseCase;

    @Description("유저 아트 정보 업로드 및 게시글 (모루 + 파츠 + 공간 + 게시글)")
    @Secured(value = "ROLE_USER")
    @PostMapping("/moru/arts")
    public void registerAvatar(@AccessAccount CurrentAccount account,
                               @RequestBody AvatarRequest avatarRequest) {
        itemCommandUseCase.saveAvatar(avatarRequest.toCommand(account.getUserEmail()));
    }

    @Description("마이페이지 조회")
    @Secured(value = "ROLE_USER")
    @GetMapping("/mypage")
    public MyPageData getMyPage(@AccessAccount CurrentAccount account) {
        return myPageQueryUseCase.getMyPageInfo(account.getUserEmail());
    }

    @Description("마이페이지 > 타입별 작품, 파츠 목록 불러오기")
    @Secured(value = "ROLE_USER")
    @PostMapping("/mypage/arts")
    public PageableData<List<MyPageArtData>> getMyArts(@AccessAccount CurrentAccount account,
                                                       @RequestBody MyArtsSearchRequest request) {
        return myPageQueryUseCase.getMyArtInfos(request.toCommand(account.getUserEmail()));
    }

    @Description("마이페이지 > 관리자 > 게시글 삭제")
    @Secured(value = "ROLE_USER")
    @DeleteMapping("/mypage")
    public void deleteMyBoard(@AccessAccount CurrentAccount account,
                              @RequestParam("boardKey") Long boardKey) {
        myPageCommandUseCase.deleteMyBoard(boardKey);
    }
}
