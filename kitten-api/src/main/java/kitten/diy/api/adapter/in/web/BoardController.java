package kitten.diy.api.adapter.in.web;

import kitten.core.coredomain.page.PageableData;
import kitten.diy.api.adapter.in.web.request.BoardSearchRequest;
import kitten.diy.api.application.port.in.BoardQueryUseCase;
import kitten.diy.api.application.port.in.query.BoardDetailData;
import kitten.diy.api.application.port.in.query.BoardInfoData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardQueryUseCase boardQueryUseCase;

    @PostMapping("/list")
    public PageableData<List<BoardInfoData>> getBoardList(@RequestBody BoardSearchRequest request) {
        return boardQueryUseCase.getBoardInfos(request.toCommand());
    }

    @GetMapping("/detail/{boardKey}")
    public BoardDetailData getBoardDetail(@PathVariable("boardKey") Long boardKey) {
        return boardQueryUseCase.getDetailData(boardKey);
    }
}
