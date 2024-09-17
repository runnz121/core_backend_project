package kitten.diy.api.application.port.in.query;

import kitten.diy.api.application.port.in.command.command.PartsSearchCommand;
import kitten.diy.api.application.port.in.query.data.PartsThemeData;

import java.util.List;

public interface ItemQueryUseCase {

    List<PartsThemeData> getPartsByTheme(PartsSearchCommand command);
}
