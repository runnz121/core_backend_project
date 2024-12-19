package kitten.diy.api.application.port.in.query.data;

public record LogoutResultData(

        String result
) {

    private static final String SUCCESS = "SUCCESS";

    public static LogoutResultData success() {
        return new LogoutResultData(SUCCESS);
    }
}
