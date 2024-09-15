package kitten.core.corecommon.utils;

public class AuthUtil {
    public static final int MAX_AGE = 24 * 60 * 60 * 100;
    public static final String REDIRECT_URL = "http://localhost:3000/signup";
    public static final String DOMAIN = "diykitten.com";
    public static final String ISSUER = "diykitten.com";
    public static final String TOKEN_SUBJECT = "kttten";
    public static final String ROLE = "role";
    public static final Long NOT_EXPIRED = 3600 * 24 * 30 * 12 * 999L;
    public static final String USER_EMAIL = "user_email";
    public static final String REDIRECT_URI = "redirect_uri";
    public static final String DEFAULT_PATH = "/";
    public static final String ERROR = "error";
    public static final String OAUTH_REQUEST_COOKIE = "oauth2_auth_request";
    public static final Integer COOKIE_EXPIRATION = 100;
    public static final String KITTEN_COOKIE_NAME = "kitten-cookie";
}
