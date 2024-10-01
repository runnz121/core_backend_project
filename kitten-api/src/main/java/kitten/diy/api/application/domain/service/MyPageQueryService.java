package kitten.diy.api.application.domain.service;

import kitten.core.coredomain.page.PageableData;
import kitten.diy.api.application.port.in.command.command.MyArtSearchCommand;
import kitten.diy.api.application.port.in.query.MyPageQueryUseCase;
import kitten.diy.api.application.port.in.query.data.MyPageArtData;
import kitten.diy.api.application.port.in.query.data.MyPageData;
import kitten.diy.api.application.port.out.MyPageFetchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageQueryService implements MyPageQueryUseCase {

    private final MyPageFetchPort myPageFetchPort;

    @Override
    public MyPageData getMyPageInfo(String userEmail) {
        return myPageFetchPort.getMyPageData(userEmail);
    }

    @Override
    public PageableData<List<MyPageArtData>> getMyArtInfos(MyArtSearchCommand command) {
        return myPageFetchPort.getMyArtInfos(command);
    }
}
