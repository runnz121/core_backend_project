package kitten.diy.api.adapter.out.persistence.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kitten.diy.api.application.port.in.command.command.TagLikeSearchCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kitten.core.coredomain.moru.entity.QMoruPartsTag.moruPartsTag;

@Repository
@RequiredArgsConstructor
public class ItemQueryFetchImpl implements ItemQueryFetch {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<String> getPartsTagLikes(TagLikeSearchCommand command) {
        return jpaQueryFactory.select(moruPartsTag.tag)
                .from(moruPartsTag)
                .where(moruPartsTag.tag.contains(command.searchTag()))
                .limit(command.limit())
                .fetch();
    }
}
