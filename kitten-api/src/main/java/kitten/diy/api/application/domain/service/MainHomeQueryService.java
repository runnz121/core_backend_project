package kitten.diy.api.application.domain.service;


import kitten.core.coredomain.page.PageData;
import kitten.core.coredomain.page.PageableData;
import kitten.diy.api.adapter.out.model.MainHomeQueryData;
import kitten.diy.api.application.port.in.MainHomeQueryUseCase;
import kitten.diy.api.application.port.in.command.HomeInfoSearchCommand;
import kitten.diy.api.application.port.in.query.MainHomeInfoData;
import kitten.diy.api.application.port.out.MainHomePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainHomeQueryService implements MainHomeQueryUseCase {

    private final MainHomePort mainHomePort;

    @Override
    public PageableData<List<MainHomeInfoData>> getMainHomeInfos(HomeInfoSearchCommand command) {
        Page<MainHomeQueryData> mainHomeBoardPages = mainHomePort.getMainHomeBoardDatas(command);
        List<MainHomeInfoData> mainHomeBoardList = mainHomeBoardPages.stream().map(MainHomeInfoData::of).toList();
        return new PageableData<>(mainHomeBoardList, PageData.of(mainHomeBoardPages));
    }
}
