package kitten.diy.api.application.port.in.command.command;

public record BoardLikeCommand(

        Long boardKey,

        String userName
) {

    public static BoardLikeCommand of(Long boardKey,
                                      String userName) {
        return new BoardLikeCommand(boardKey, userName);
    }
}
