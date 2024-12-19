package kitten.diy.api.application.port.in.command.command;

import kitten.core.coredomain.item.consts.ItemType;
import kitten.core.coredomain.theme.consts.ThemeType;

public record ItemSearchCommand(

        ThemeType themeType,

        ItemType itemType
) {

    public static ItemSearchCommand of(String itemType,
                                        String themType) {
        return new ItemSearchCommand(ThemeType.valueOf(themType), ItemType.valueOf(itemType));
    }
}
