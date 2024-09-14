package kitten.diy.api.application.domain.service;


import kitten.core.coredomain.page.PageData;
import kitten.core.coredomain.page.PageableData;
import kitten.diy.api.adapter.out.model.BoardQueryData;
import kitten.diy.api.application.port.in.BoardQueryUseCase;
import kitten.diy.api.application.port.in.command.BoardInfoSearchCommand;
import kitten.diy.api.application.port.in.query.BoardDetailData;
import kitten.diy.api.application.port.in.query.BoardInfoData;
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
        List<BoardInfoData> boardList = boardPages.stream().map(BoardInfoData::of).toList();
        return new PageableData<>(boardList, PageData.of(boardPages));
    }

    @Override
    public BoardDetailData getDetailData(Long boardKey) {
        return boardPort.getBoardDetail(boardKey);
    }
}
