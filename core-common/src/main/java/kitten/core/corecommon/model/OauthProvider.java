package kitten.core.corecommon.model;

import kitten.core.corecommon.model.provider.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum OauthProvider {

    GOOGLE(OauthProvider.GOOGLE_PROVIDER, GoogleOauth2UserInfo::new),
    KAKAO(OauthProvider.KAKAO_PROVIDER, KakaoOauth2UserInfo::new),
    NAVER(OauthProvider.NAVER_PROVIDER, NaverOauth2UserInfo::new),
    LINKEDIN(OauthProvider.LINKEDIN_PROVIDER, LinkedInOauth2UserInfo::new)
    ;

    public static final String GOOGLE_PROVIDER = "google";
    public static final String KAKAO_PROVIDER = "kakao";
    public static final String NAVER_PROVIDER = "naver";
    public static final String LINKEDIN_PROVIDER = "linkedin";

    private final String providerId;
    private final Function<Map<String, Object>, Oauth2UserInfo> userInfoFunction;

    public static OauthProvider getProvider(String registrationId) {
        return Arrays.stream(OauthProvider.values())
                .filter(provider -> registrationId.equalsIgnoreCase(provider.providerId))
                .findFirst()
                .orElseThrow();
    }

    public Oauth2UserInfo toOauthProvide(Map<String, Object> attributes) {
        return userInfoFunction.apply(attributes);
    }
}
