package kitten.diy.api.application.domain.service;


import kitten.core.coredomain.page.PageData;
import kitten.core.coredomain.page.PageableData;
import kitten.diy.api.adapter.out.model.BoardQueryData;
import kitten.diy.api.application.port.in.command.command.TagLikeSearchCommand;
import kitten.diy.api.application.port.in.query.BoardQueryUseCase;
import kitten.diy.api.application.port.in.command.command.BoardInfoSearchCommand;
import kitten.diy.api.application.port.in.query.data.BoardDetailData;
import kitten.diy.api.application.port.in.query.data.BoardInfoData;
import kitten.diy.api.application.port.in.query.data.BoardLikeUsersData;
import kitten.diy.api.application.port.out.BoardPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardQueryService implements BoardQueryUseCase {

    private final BoardPort boardPort;

    @Override
    public PageableData<List<BoardInfoData>> getBoardInfos(BoardInfoSearchCommand command) {
        Page<BoardQueryData> boardPages = boardPort.getBoardDatas(command);
        List<BoardInfoData> boardList = boardPages.stream().map(board -> BoardInfoData.of(board, boardPort.getTagsByBoardKey(board.boardKey()))).toList();
        return new PageableData<>(boardList, PageData.of(boardPages));
    }

    @Override
    public BoardDetailData getDetailData(Long boardKey) {
        return boardPort.getBoardDetail(boardKey);
    }

    @Override
    public List<BoardLikeUsersData> getBoardLikeUsers(Long boardKey) {
        return boardPort.getBoardLikeUsers(boardKey);
    }

    @Override
    public List<String> getLikeTags(TagLikeSearchCommand command) {
        return boardPort.getLikeTags(command);
    }

    @Override
    public void getPartsInfos(Long boardKey) {
        boardPort.getBoardPartsInfos(boardKey);
    }
}
