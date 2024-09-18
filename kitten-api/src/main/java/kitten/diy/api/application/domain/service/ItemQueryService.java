package kitten.diy.api.application.domain.service;

import kitten.diy.api.application.port.in.command.command.ItemSearchCommand;
import kitten.diy.api.application.port.in.query.ItemQueryUseCase;
import kitten.diy.api.application.port.in.query.data.ItemThemeData;
import kitten.diy.api.application.port.out.ItemFetchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemQueryService implements ItemQueryUseCase {

    private final ItemFetchPort itemFetchPort;

    @Override
    public ItemThemeData getThemeItemData(ItemSearchCommand command) {
        return itemFetchPort.getItemThemeData(command);
    }
}
