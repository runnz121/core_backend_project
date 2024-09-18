package kitten.diy.api.application.port.out;

import kitten.diy.api.application.port.in.command.command.PartsSearchCommand;
import kitten.diy.api.application.port.in.query.data.PartsThemeData;

import java.util.List;

public interface PartsFetchPort {

    List<PartsThemeData> getPartsByTheme(PartsSearchCommand command);
}
