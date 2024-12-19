package kitten.core.corecommon.model.provider;

public sealed interface Oauth2UserInfo permits
        GoogleOauth2UserInfo, KakaoOauth2UserInfo, LinkedInOauth2UserInfo, NaverOauth2UserInfo {

    String getId();

    String getNickName();

    String getEmail();
}
