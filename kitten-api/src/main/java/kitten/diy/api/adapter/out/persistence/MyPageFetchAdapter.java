package kitten.diy.api.adapter.out.persistence;

import kitten.core.corecommon.config.exception.CommonRuntimeException;
import kitten.core.coredomain.board.repository.BoardLikeRepository;
import kitten.core.coredomain.board.repository.BoardRepository;
import kitten.core.coredomain.board.repository.BoardViewRepository;
import kitten.core.coredomain.user.entity.Users;
import kitten.core.coredomain.user.repository.UsersRepository;
import kitten.diy.api.adapter.out.error.UserErrorCode;
import kitten.diy.api.adapter.out.model.MyLikeRankInfo;
import kitten.diy.api.adapter.out.model.MyViewRankInfo;
import kitten.diy.api.adapter.out.persistence.query.MyPageQueryFetch;
import kitten.diy.api.application.port.in.query.data.MyPageData;
import kitten.diy.api.application.port.out.MyPageFetchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MyPageFetchAdapter implements MyPageFetchPort {

    private final UsersRepository usersRepository;
    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final BoardViewRepository boardViewRepository;
    private final MyPageQueryFetch myPageQueryFetch;

    @Override
    @Transactional(readOnly = true)
    public MyPageData getMyPageData(String userEmail) {

        Users users = getUsers(userEmail);

        // like
        List<MyLikeRankInfo> allLikeRankInfos = myPageQueryFetch.getLikeCounts();

        int myLikeCount = (int) getMyLikeCount(allLikeRankInfos, users);

        int myLikeRank = getMyLikeRank(groupingMyLikeCount(allLikeRankInfos), users.getKey());

        // view
        List<MyViewRankInfo> allViewRankInfos = myPageQueryFetch.getViewCounts();

        int myViewRank = getMyViewRank(groupingMyViewCount(allViewRankInfos), users.getKey());

        return MyPageData.builder()
                .nickName(users.getNickName())
                .profileImgUrl(users.getProfileImgUrl())
                .myLikeCount(myLikeCount)
                .myLikeRank(myLikeRank)
                .myViewRank(myViewRank)
                .build();
    }

    @Transactional(readOnly = true)
    public Users getUsers(String userEmail) {
        return usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CommonRuntimeException(UserErrorCode.USER_NOT_EXISTS));
    }

    public long getMyLikeCount(List<MyLikeRankInfo> likeRankInfos,
                               Users users) {
        return likeRankInfos
                .stream()
                .filter(it -> it.userKey().equals(users.getKey()))
                .map(MyLikeRankInfo::likeBoardCount)
                .toList()
                .stream()
                .reduce(Long::sum)
                .orElse(0L);
    }

    public int getMyLikeRank(Map<Long, Long> sortedLikeCountMap, Long userKey) {
        return sortedLikeCountMap.keySet()
                .stream()
                .toList()
                .indexOf(userKey) + 1;
    }

    public Map<Long, Long> groupingMyLikeCount(List<MyLikeRankInfo> likeRankInfos) {
        Map<Long, Long> likeCountMap = likeRankInfos.stream()
                .collect(Collectors.groupingBy(
                        MyLikeRankInfo::userKey,
                        Collectors.summingLong(MyLikeRankInfo::likeBoardCount)
                ));

        return likeCountMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public int getMyViewRank(Map<Long, Long> sortedViewCountMap, Long userKey) {
        return sortedViewCountMap.keySet()
                .stream()
                .toList()
                .indexOf(userKey) + 1;
    }

    public Map<Long, Long> groupingMyViewCount(List<MyViewRankInfo> viewRankInfos) {
        Map<Long, Long> viewCountMap = viewRankInfos.stream()
                .collect(Collectors.groupingBy(
                        MyViewRankInfo::userKey,
                        Collectors.summingLong(MyViewRankInfo::viewBoardCount)
                ));

        return viewCountMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
