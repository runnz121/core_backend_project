package kitten.diy.api.adapter.in.web.request;

import kitten.core.corecommon.annotation.Description;
import kitten.core.coredomain.user.consts.MyArtType;
import kitten.diy.api.application.port.in.command.command.MyArtSearchCommand;
import org.springframework.data.domain.PageRequest;

public record MyArtsSearchRequest(

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

            @Description("필터링 조건[MY_ARTS, MY_LIKE_ARTS, MY_PARTS]")
            String searchType
    ) {
    }

    public MyArtSearchCommand toCommand(String userEmail) {
        return MyArtSearchCommand.builder()
                .pageRequest(
                        PageRequest.of(
                                paging.no,
                                paging.limit
                        )
                )
                .searchType(MyArtType.of(criteria.searchType))
                .userEmail(userEmail)
                .build();
    }
}
