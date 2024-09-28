package kitten.diy.api.adapter.out.persistence;

import kitten.core.coredomain.moru.repository.MoruRepository;
import kitten.core.coredomain.theme.repository.ThemeRepository;
import kitten.diy.api.adapter.out.persistence.query.ItemQueryFetch;
import kitten.diy.api.application.port.in.command.command.ItemSearchCommand;
import kitten.diy.api.application.port.in.command.command.TagLikeSearchCommand;
import kitten.diy.api.application.port.in.query.data.ItemThemeData;
import kitten.diy.api.application.port.out.ItemFetchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ItemFetchAdapter implements ItemFetchPort {

    private final ThemeRepository themeRepository;
    private final MoruRepository moruRepository;
    private final ItemQueryFetch itemQueryFetch;

    @Override
    @Transactional(readOnly = true)
    public ItemThemeData getItemThemeData(ItemSearchCommand command) {
        return themeRepository.findByType(command.themeType())
                .map(theme -> {
                    return moruRepository.findByTheme(theme)
                            .map(ItemThemeData::createMoruItemData)
                            .orElseGet(() -> ItemThemeData.EMPTY);
                })
                .orElseGet(() -> ItemThemeData.EMPTY);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getPartsLikeTags(TagLikeSearchCommand command) {
        return itemQueryFetch.getPartsTagLikes(command)
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
