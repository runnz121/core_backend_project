package kitten.diy.api.application.port.in.query;

import kitten.diy.api.application.port.in.command.command.ItemSearchCommand;
import kitten.diy.api.application.port.in.command.command.TagLikeSearchCommand;
import kitten.diy.api.application.port.in.query.data.ItemThemeData;

import java.util.List;

public interface ItemQueryUseCase {

    List<ItemThemeData> getThemeItemData(ItemSearchCommand command);

    List<String> getLikePartsTags(TagLikeSearchCommand command);
}
