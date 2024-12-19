package kitten.diy.api.application.port.out;

import kitten.diy.api.application.port.in.command.command.ItemSearchCommand;
import kitten.diy.api.application.port.in.command.command.TagLikeSearchCommand;
import kitten.diy.api.application.port.in.query.data.ItemThemeData;

import java.util.List;

public interface ItemFetchPort {

    List<ItemThemeData> getItemThemeData(ItemSearchCommand command);

    List<String> getPartsLikeTags(TagLikeSearchCommand command);
}
