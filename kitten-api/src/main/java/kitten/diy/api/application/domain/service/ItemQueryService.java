package kitten.diy.api.application.domain.service;

import kitten.diy.api.application.port.in.command.command.PartsSearchCommand;
import kitten.diy.api.application.port.in.query.ItemQueryUseCase;
import kitten.diy.api.application.port.in.query.data.PartsThemeData;
import kitten.diy.api.application.port.out.ItemPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemQueryService implements ItemQueryUseCase {

    private final ItemPort itemPort;

    @Override
    public List<PartsThemeData> getPartsByTheme(PartsSearchCommand command) {
        return itemPort.getPartsByTheme(command);
    }
}
