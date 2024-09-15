package kitten.diy.api.application.port.in.query.data;

public record BoardLikeUsersData(

        String nickName,

        String profileImageUrl
) {

    public static BoardLikeUsersData of(String nickName,
                                        String profileImageUrl) {
        return new BoardLikeUsersData(nickName, profileImageUrl);
    }
}
