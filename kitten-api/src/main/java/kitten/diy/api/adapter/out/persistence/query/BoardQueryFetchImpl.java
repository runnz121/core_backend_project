package kitten.diy.api.adapter.out.persistence.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kitten.core.coredomain.board.entity.QBoard;
import kitten.core.coredomain.board.entity.QBoardLike;
import kitten.core.coredomain.board.entity.QBoardView;
import kitten.diy.api.adapter.out.model.BoardQueryData;
import kitten.diy.api.adapter.out.model.QBoardQueryData;
import kitten.diy.api.application.port.in.command.command.BoardInfoSearchCommand;
import kitten.diy.api.application.port.in.command.command.TagLikeSearchCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kitten.core.coredomain.board.entity.QBoard.board;
import static kitten.core.coredomain.board.entity.QBoardImage.boardImage;
import static kitten.core.coredomain.board.entity.QBoardLike.boardLike;
import static kitten.core.coredomain.board.entity.QBoardTag.boardTag;
import static kitten.core.coredomain.board.entity.QBoardView.boardView;


@Repository
@RequiredArgsConstructor
public class BoardQueryFetchImpl implements BoardQueryFetch {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<BoardQueryData> getMainHomeDatas(BoardInfoSearchCommand command) {
        JPAQuery<BoardQueryData> datas = jpaQueryFactory.select(
                new QBoardQueryData(
                        board.key,
                        boardImage.imageUrl,
                        board.type,
                        board.createTime,
                        getLikeCount(boardLike),
                        getViewCount(boardView)
                ))
                .distinct()
                .from(board)
                .leftJoin(boardImage)
                .on(boardImage.board.eq(board)
                        .and(board.deleted.isFalse())
                        .and(boardImage.deleted.isFalse())
                        .and(boardImage.representative.isTrue())
                )
                .leftJoin(boardTag)
                .on(boardTag.board.eq(board))
                .leftJoin(boardView)
                .on(boardView.board.eq(board))
                .where(
                        board.deleted.isFalse(),
                        searchByTag(command),
                        filterByType(command)
                )
                .orderBy(getOrderBy(command, boardLike, boardView, board));

        int size = datas.fetch().size();

        List<BoardQueryData> fetchQueryDatas = datas
                .offset(command.pageRequest().getOffset())
                .limit(command.pageRequest().getPageSize())
                .fetch();
        return new PageImpl<>(fetchQueryDatas, command.pageRequest(), size);
    }

    @Override
    public List<String> getTagLikes(TagLikeSearchCommand command) {
        return jpaQueryFactory.select(boardTag.tag)
                .from(boardTag)
                .where(boardTag.tag.contains(command.searchTag()))
                .limit(command.limit())
                .fetch();
    }

    private JPQLQuery<Long> getLikeCount(QBoardLike like) {
        return JPAExpressions
                .select(like.key.count())
                .from(like);
    }

    private JPQLQuery<Long> getViewCount(QBoardView view) {
        return JPAExpressions
                .select(view.key.count())
                .from(view);
    }

    private BooleanBuilder searchByTag(BoardInfoSearchCommand command) {
        BooleanBuilder br = new BooleanBuilder();
        if (command.isNotSearchByTag()) {
            return null;
        }
        command.searchTags().forEach(searchTag -> br.or(boardTag.tag.eq(searchTag)));
        return br;
    }

    private BooleanBuilder filterByType(BoardInfoSearchCommand command) {
        BooleanBuilder br = new BooleanBuilder();
        if (command.isFilterBySearchType() == false) {
            return null;
        }
        return br.and(board.type.eq(command.searchType()));
    }

    private OrderSpecifier[] getOrderBy(BoardInfoSearchCommand command,
                                        QBoardLike boardLike,
                                        QBoardView boardView,
                                        QBoard board) {
        return switch (command.sortType()) {
            case LIKE -> new OrderSpecifier[]{new OrderSpecifier<>(Order.DESC, getLikeCount(boardLike))};
            case VIEW -> new OrderSpecifier[]{new OrderSpecifier<>(Order.DESC, getViewCount(boardView))};
            case RECENT -> new OrderSpecifier[]{board.createTime.desc()};
        };
    }
}
