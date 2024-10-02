package kitten.diy.api.application.domain.service;

import kitten.diy.api.application.port.in.command.MyPageCommandUseCase;
import kitten.diy.api.application.port.out.MyPagePersistentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageCommandService implements MyPageCommandUseCase {

    private final MyPagePersistentPort myPagePersistentPort;

    @Override
    public void deleteMyBoard(Long boardKey) {
        myPagePersistentPort.deleteBoard(boardKey);
    }
}
