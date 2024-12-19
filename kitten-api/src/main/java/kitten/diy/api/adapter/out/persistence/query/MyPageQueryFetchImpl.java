package kitten.diy.api.adapter.out.persistence.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kitten.diy.api.adapter.out.model.MyLikeRankInfo;
import kitten.diy.api.adapter.out.model.MyViewRankInfo;
import kitten.diy.api.adapter.out.model.QMyLikeRankInfo;
import kitten.diy.api.adapter.out.model.QMyViewRankInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.core.types.ExpressionUtils.count;
import static kitten.core.coredomain.board.entity.QBoardLike.boardLike;
import static kitten.core.coredomain.board.entity.QBoardView.boardView;

@Repository
@RequiredArgsConstructor
public class MyPageQueryFetchImpl implements MyPageQueryFetch {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MyLikeRankInfo> getLikeCounts() {
        return jpaQueryFactory.select(
                new QMyLikeRankInfo(
                        count(boardLike.board.key),
                        boardLike.board.key,
                        boardLike.users.key
                ))
                .from(boardLike)
                .groupBy(boardLike.board.key, boardLike.users.key)
                .fetch();
    }

    @Override
    public List<MyViewRankInfo> getViewCounts() {
        return jpaQueryFactory.select(
                        new QMyViewRankInfo(
                                count(boardView.board.key),
                                boardView.board.key,
                                boardView.users.key
                        ))
                .from(boardView)
                .groupBy(boardView.board.key, boardView.users.key)
                .fetch();
    }
}
