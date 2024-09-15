package kitten.diy.api.adapter.in.web;

import kitten.core.coredomain.page.PageableData;
import kitten.diy.api.adapter.in.web.request.BoardSearchRequest;
import kitten.diy.api.application.port.in.command.BoardCommandUseCase;
import kitten.diy.api.application.port.in.query.BoardQueryUseCase;
import kitten.diy.api.application.port.in.query.data.BoardDetailData;
import kitten.diy.api.application.port.in.query.data.BoardInfoData;
import kitten.diy.api.application.port.in.query.data.BoardLikeUsersData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardCommandUseCase boardCommandUseCase;
    private final BoardQueryUseCase boardQueryUseCase;

    @PostMapping("/list")
    public PageableData<List<BoardInfoData>> getBoardList(@RequestBody BoardSearchRequest request) {
        return boardQueryUseCase.getBoardInfos(request.toCommand());
    }

    @GetMapping("/detail/{boardKey}")
    public BoardDetailData getBoardDetail(@PathVariable("boardKey") Long boardKey) {
        // 접속시 조회수 증가하도록 조건 추가
        //boardCommandUseCase
        return boardQueryUseCase.getDetailData(boardKey);
    }

    @GetMapping("/detail/{boardKey}/like/users")
    public  List<BoardLikeUsersData> getBoardLikeUsers(@PathVariable("boardKey") Long boardKey) {
        return boardQueryUseCase.getBoardLikeUsers(boardKey);
    }
}
