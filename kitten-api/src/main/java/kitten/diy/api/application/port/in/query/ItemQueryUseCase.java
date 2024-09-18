package kitten.diy.api.application.port.in.query;

import kitten.diy.api.application.port.in.command.command.ItemSearchCommand;
import kitten.diy.api.application.port.in.query.data.ItemThemeData;

public interface ItemQueryUseCase {

    ItemThemeData getThemeItemData(ItemSearchCommand command);
}
