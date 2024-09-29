package kitten.diy.api.adapter.in.web;

import kitten.core.corecommon.annotation.Description;
import kitten.core.corecommon.security.jwt.AccessAccount;
import kitten.core.corecommon.security.jwt.CurrentAccount;
import kitten.core.coredomain.page.PageableData;
import kitten.diy.api.adapter.in.web.request.BoardSearchRequest;
import kitten.diy.api.adapter.in.web.request.TagLikeSearchRequest;
import kitten.diy.api.application.port.in.command.BoardCommandUseCase;
import kitten.diy.api.application.port.in.command.command.BoardLikeCommand;
import kitten.diy.api.application.port.in.query.BoardQueryUseCase;
import kitten.diy.api.application.port.in.query.data.BoardDetailData;
import kitten.diy.api.application.port.in.query.data.BoardInfoData;
import kitten.diy.api.application.port.in.query.data.BoardLikeUsersData;
import kitten.diy.api.application.port.in.query.data.BoardPartsInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardCommandUseCase boardCommandUseCase;
    private final BoardQueryUseCase boardQueryUseCase;

    @Description("게시글 리스트 조회")
    @PostMapping("/list")
    @Secured(value = {"ROLE_USER", "ROLE_ANONYMOUS"})
    public PageableData<List<BoardInfoData>> getBoardList(@RequestBody BoardSearchRequest request) {
        return boardQueryUseCase.getBoardInfos(request.toCommand());
    }

    @Description("게시글 상세 정보 조회")
    @GetMapping("/detail/{boardKey}")
    public BoardDetailData getBoardDetail(@PathVariable("boardKey") Long boardKey) {
        // 접속시 조회수 증가하도록 조건 추가
        //boardCommandUseCase
        return boardQueryUseCase.getDetailData(boardKey);
    }

    @Description("게시글 상세 좋아요 한 유저 리스트 조회")
    @GetMapping("/detail/{boardKey}/like/users")
    public List<BoardLikeUsersData> getBoardLikeUsers(@PathVariable("boardKey") Long boardKey) {
        return boardQueryUseCase.getBoardLikeUsers(boardKey);
    }

    @Description("게시글 상세 사용한 파츠 리스트 조회")
    @GetMapping("/detail/{boardKey}/parts")
    public List<BoardPartsInfo> getPartsInfos(@PathVariable Long boardKey) {
        return boardQueryUseCase.getPartsInfos(boardKey);
    }

    @Description("비슷한 태그 조회")
    @PostMapping("/like/tags")
    public List<String> getLikeTags(@RequestBody TagLikeSearchRequest request) {
        return boardQueryUseCase.getLikeTags(request.toCommand());
    }

    @Description("게시글 좋아요")
//    @Secured(value = "ROLE_USER")
    @PostMapping("/{boardKey}/like")
    public Boolean likeBoard(@AccessAccount CurrentAccount currentAccount,
                             @PathVariable("boardKey") Long boardKey) {
        currentAccount = CurrentAccount.defaultValue();
        return boardCommandUseCase.likeBoard(BoardLikeCommand.of(boardKey, currentAccount.getUserEmail()));
    }
}
