package kitten.diy.api.application.domain.service;


import kitten.core.coredomain.board.entity.Board;
import kitten.core.coredomain.page.PageData;
import kitten.core.coredomain.page.PageableData;
import kitten.core.coredomain.user.entity.Users;
import kitten.diy.api.adapter.out.model.BoardQueryData;
import kitten.diy.api.application.port.in.command.command.TagLikeSearchCommand;
import kitten.diy.api.application.port.in.query.BoardQueryUseCase;
import kitten.diy.api.application.port.in.command.command.BoardInfoSearchCommand;
import kitten.diy.api.application.port.in.query.data.*;
import kitten.diy.api.application.port.out.BoardFetchPort;
import kitten.diy.api.application.port.out.UserFetchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardQueryService implements BoardQueryUseCase {

    private final BoardFetchPort boardFetchPort;

    @Override
    @Transactional
    public PageableData<List<BoardInfoData>> getBoardInfos(BoardInfoSearchCommand command) {
        Page<BoardQueryData> boardPages = boardFetchPort.getBoardDatas(command);
        List<BoardInfoData> boardList = boardPages.stream()
                .map(board -> {
                    Board boards = boardFetchPort.getBoard(board.boardKey());
                    return BoardInfoData.of(
                            board,
                            boardFetchPort.getTagsByBoardKey(board.boardKey()),
                            boardFetchPort.getIsMyLike(boards, command.userEmail()),
                            boards.getUser()
                    );
                })
                .toList();
        return new PageableData<>(boardList, PageData.of(boardPages));
    }

    @Override
    public BoardDetailData getDetailData(Long boardKey,
                                         String userEmai) {
        return boardFetchPort.getBoardDetail(boardKey, userEmai);
    }

    @Override
    public List<BoardLikeUsersData> getBoardLikeUsers(Long boardKey) {
        return boardFetchPort.getBoardLikeUsers(boardKey);
    }

    @Override
    public List<String> getLikeTags(TagLikeSearchCommand command) {
        return boardFetchPort.getLikeTags(command).stream()
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<BoardPartsInfo> getPartsInfos(Long boardKey) {
        return boardFetchPort.getBoardPartsInfos(boardKey);
    }

    @Override
    public MyArtDetailData getMyArtDetail(Long boardKey,
                                          String userEmail) {
        return boardFetchPort.getMyArtDetail(boardKey, userEmail);
    }
}
