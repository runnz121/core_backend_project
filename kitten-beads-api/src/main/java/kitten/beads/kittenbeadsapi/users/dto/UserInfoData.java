package kitten.beads.kittenbeadsapi.users.dto;

public record UserInfoData(

        // user 이메일
        String email,

        // 토큰 정보
        Integer restToken

) {

    public static UserInfoData of(String email,
                                  Integer restToken) {
        return new UserInfoData(email, restToken);
    }
}
