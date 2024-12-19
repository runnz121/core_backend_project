package kitten.diy.api.adapter.out.persistence;

import kitten.core.corecommon.config.exception.CommonRuntimeException;
import kitten.core.coredomain.board.entity.Board;
import kitten.core.coredomain.board.repository.BoardRepository;
import kitten.core.coredomain.user.entity.Users;
import kitten.core.coredomain.user.repository.UsersRepository;
import kitten.diy.api.adapter.out.error.UserErrorCode;
import kitten.diy.api.application.port.in.command.command.MyProfileCommand;
import kitten.diy.api.application.port.out.MyPagePersistentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MyPagePersistentAdapter implements MyPagePersistentPort {

    private final BoardRepository boardRepository;
    private final UsersRepository usersRepository;

    @Override
    @Transactional
    public void deleteBoard(Long boardKey) {
        boardRepository.findByKeyAndDeletedIsFalse(boardKey).ifPresent(
                Board::deleteBoard
        );
    }

    @Override
    @Transactional
    public void modifyMyProfile(MyProfileCommand command) {
        Users users = usersRepository.findByEmail(command.userEmail())
                .orElseThrow(() -> new CommonRuntimeException(UserErrorCode.USER_NOT_EXISTS));
        users.changeNickName(command.nickName())
              .changeProfileImgUrl(command.profileImgUrl());
    }
}
