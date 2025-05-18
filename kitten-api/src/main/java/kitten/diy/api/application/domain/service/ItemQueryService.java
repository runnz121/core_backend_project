package kitten.diy.api.application.domain.service;

import kitten.diy.api.application.port.in.command.command.ItemSearchCommand;
import kitten.diy.api.application.port.in.command.command.TagLikeSearchCommand;
import kitten.diy.api.application.port.in.query.ItemQueryUseCase;
import kitten.diy.api.application.port.in.query.data.ItemThemeData;
import kitten.diy.api.application.port.out.ItemFetchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemQueryService implements ItemQueryUseCase {

    private final ItemFetchPort itemFetchPort;

    /**
     * 캐시 데이터 미 존재시 실 데이터 조회 반환
     *
     * @param command
     * @return
     */
    @Override
    public List<ItemThemeData> getThemeItemData(ItemSearchCommand command) {

        return itemFetchPort.getCachedItemThemeData(command);
    }

    @Override
    public List<String> getLikePartsTags(TagLikeSearchCommand command) {
        return itemFetchPort.getPartsLikeTags(command);
    }
}
