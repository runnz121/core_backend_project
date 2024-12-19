package kitten.diy.api.application.port.in.query.data;

public record CheckNickNameData(

        Boolean isExists,

        String desc
) {

    private static final String ALREADY_EXISTS = "이미 존재하는 닉네임 입니다.";
    private static final String ACCEPTABLE_NICK_NAME = "사용 가능한 닉네임 입니다.";

    public static CheckNickNameData of(Boolean isExists) {
        return new CheckNickNameData(isExists, isExists ? ALREADY_EXISTS : ACCEPTABLE_NICK_NAME);
    }
}
