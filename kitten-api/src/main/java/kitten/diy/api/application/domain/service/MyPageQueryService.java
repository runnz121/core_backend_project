package kitten.diy.api.application.domain.service;

import kitten.diy.api.application.port.in.query.MyPageQueryUseCase;
import kitten.diy.api.application.port.in.query.data.MyPageData;
import kitten.diy.api.application.port.out.MyPageFetchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageQueryService implements MyPageQueryUseCase {

    private final MyPageFetchPort myPageFetchPort;

    @Override
    public MyPageData getMyPageInfo(String userEmail) {
        return myPageFetchPort.getMyPageData(userEmail);
    }
}
