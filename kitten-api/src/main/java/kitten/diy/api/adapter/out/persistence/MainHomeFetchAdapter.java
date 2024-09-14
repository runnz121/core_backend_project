package kitten.diy.api.adapter.out.persistence;

import kitten.diy.api.adapter.out.model.MainHomeQueryData;
import kitten.diy.api.adapter.out.persistence.query.MainHomeQueryFetch;
import kitten.diy.api.application.port.in.command.HomeInfoSearchCommand;
import kitten.diy.api.application.port.out.MainHomePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MainHomeFetchAdapter implements MainHomePort {

    private final MainHomeQueryFetch mainHomeQueryFetch;

    @Override
    @Transactional(readOnly = true)
    public Page<MainHomeQueryData> getMainHomeBoardDatas(HomeInfoSearchCommand command) {
        return mainHomeQueryFetch.getMainHomeDatas(command);
    }
}
