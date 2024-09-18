package kitten.diy.api.application.port.out;

import kitten.diy.api.application.port.in.command.command.ItemSearchCommand;
import kitten.diy.api.application.port.in.query.data.ItemThemeData;

public interface ItemFetchPort {

    ItemThemeData getItemThemeData(ItemSearchCommand command);
}
