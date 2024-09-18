package kitten.diy.api.application.domain.service;

import kitten.diy.api.application.port.in.command.command.PartsSearchCommand;
import kitten.diy.api.application.port.in.query.PartsQueryUseCase;
import kitten.diy.api.application.port.in.query.data.PartsThemeData;
import kitten.diy.api.application.port.out.PartsFetchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartsQueryService implements PartsQueryUseCase {

    private final PartsFetchPort partsFetchPort;

    @Override
    public List<PartsThemeData> getPartsByTheme(PartsSearchCommand command) {
        return partsFetchPort.getPartsByTheme(command);
    }
}
