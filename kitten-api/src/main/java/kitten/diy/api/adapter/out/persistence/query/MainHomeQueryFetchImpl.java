package kitten.diy.api.adapter.out.persistence.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kitten.core.coredomain.board.QBoard;
import kitten.core.coredomain.board.QBoardLike;
import kitten.core.coredomain.board.QBoardView;
import kitten.core.coredomain.config.orm.QuerydslConfiguration;
import kitten.diy.api.adapter.out.model.MainHomeQueryData;
import kitten.diy.api.adapter.out.model.QMainHomeQueryData;
import kitten.diy.api.application.port.in.command.HomeInfoSearchCommand;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kitten.core.coredomain.board.QBoard.board;
import static kitten.core.coredomain.board.QBoardImage.boardImage;
import static kitten.core.coredomain.board.QBoardLike.boardLike;
import static kitten.core.coredomain.board.QBoardTag.boardTag;
import static kitten.core.coredomain.board.QBoardView.boardView;

@Repository
@RequiredArgsConstructor
public class MainHomeQueryFetchImpl implements MainHomeQueryFetch {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<MainHomeQueryData> getMainHomeDatas(HomeInfoSearchCommand command) {
        JPAQuery<MainHomeQueryData> datas = jpaQueryFactory.select(
                new QMainHomeQueryData(
                        board.key,
                        boardImage.image.imageUrl,
                        board.createTime,
                        boardLike.likeCount,
                        boardView.viewCount
                ))
                .from(board)
                .leftJoin(boardImage)
                .on(boardImage.board.eq(board).and(boardImage.representative.isTrue()))
                .leftJoin(boardTag)
                .on(boardTag.board.eq(board))
                .leftJoin(boardLike)
                .on(boardLike.board.eq(board))
                .leftJoin(boardView)
                .on(boardView.board.eq(board))
                .where(
                        searchByTag(command)
                )
                .orderBy(getOrderBy(command, boardLike, boardView, board));

        int size = datas.fetch().size();

        List<MainHomeQueryData> fetchQueryDatas = datas
                .offset(command.pageRequest().getOffset())
                .limit(command.pageRequest().getPageSize())
                .fetch();
        return new PageImpl<>(fetchQueryDatas, command.pageRequest(), size);
    }

    private BooleanBuilder searchByTag(HomeInfoSearchCommand command) {
        BooleanBuilder br = new BooleanBuilder();
        if (command.isSearchByTag() == false) {
            return null;
        }
        return br.and(boardTag.tag.like(command.searchTag()));
    }

    private OrderSpecifier[] getOrderBy(HomeInfoSearchCommand command,
                                        QBoardLike boardLike,
                                        QBoardView boardView,
                                        QBoard board) {
        return switch (command.sortType()) {
            case LIKE -> new OrderSpecifier[]{boardLike.likeCount.desc()};
            case VIEW -> new OrderSpecifier[]{boardView.viewCount.desc()};
            case RECENT -> new OrderSpecifier[]{board.createTime.desc()};
        };
    }
}
