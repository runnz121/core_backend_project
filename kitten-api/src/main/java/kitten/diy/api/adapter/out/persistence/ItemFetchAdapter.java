package kitten.diy.api.adapter.out.persistence;

import kitten.core.coredomain.moru.repository.MoruRepository;
import kitten.core.coredomain.theme.repository.ThemeRepository;
import kitten.diy.api.application.port.in.command.command.ItemSearchCommand;
import kitten.diy.api.application.port.in.query.data.ItemThemeData;
import kitten.diy.api.application.port.out.ItemFetchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ItemFetchAdapter implements ItemFetchPort {

    private final ThemeRepository themeRepository;
    private final MoruRepository moruRepository;

    @Override
    @Transactional(readOnly = true)
    public ItemThemeData getItemThemeData(ItemSearchCommand command) {
        return themeRepository.findByType(command.themeType())
                .map(theme -> {
                    return moruRepository.findByTheme(theme)
                            .map(ItemThemeData::creteMoruItemData)
                            .orElseGet(() -> ItemThemeData.EMPTY);
                })
                .orElseGet(() -> ItemThemeData.EMPTY);
    }
}
