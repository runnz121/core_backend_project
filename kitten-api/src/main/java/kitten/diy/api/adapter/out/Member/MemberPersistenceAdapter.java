package kitten.diy.api.adapter.out.Member;

import kitten.core.coredomain.user.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter {

    private final UsersRepository usersRepository;


}
