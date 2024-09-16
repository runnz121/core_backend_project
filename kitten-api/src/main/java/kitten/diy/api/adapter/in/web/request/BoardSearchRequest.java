package kitten.diy.api.adapter.in.web.request;

import kitten.core.corecommon.annotation.Description;
import kitten.core.coredomain.board.consts.BoardType;
import kitten.diy.api.application.domain.consts.SortType;
import kitten.diy.api.application.port.in.command.command.BoardInfoSearchCommand;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public record BoardSearchRequest(

        @Description("페이징 처리")
        Paging paging,

        @Description("검색조건")
        Criteria criteria

) {

    public record Paging(

            @Description("페이지 넘버")
            Integer no,

            @Description("페이지 사이즈")
            Integer limit
    ) {
    }

    public record Criteria(

            @Description("정렬 타입[RECENT, LIKE, VIEW]")
            String sortType,

            @Description("태그 검색")
            List<String> searchTags,

            @Description("필터링 조건[BEADS, GEM_STITCH, MORU, NULL]")
            String searchType
    ) {
    }

    public BoardInfoSearchCommand toCommand() {
        return BoardInfoSearchCommand.builder()
                .pageRequest(
                        PageRequest.of(
                                paging.no,
                                paging.limit
                        )
                )
                .sortType(SortType.valueOf(criteria.sortType))
                .searchTags(criteria.searchTags)
                .searchType(BoardType.of(criteria.searchType))
                .build();
    }
}
