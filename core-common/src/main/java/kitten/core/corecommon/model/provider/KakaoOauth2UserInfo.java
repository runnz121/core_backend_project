package kitten.core.corecommon.model.provider;

import net.minidev.json.JSONObject;

import java.util.Map;

public record KakaoOauth2UserInfo (

        Map<String, Object> attributes

) implements Oauth2UserInfo {

    private final static String RESPONSE = "kakao_account";
    private static final String ID = "id";
    private static final String NAME = "profile.nickname";
    private static final String EMAIL = "email";

    public JSONObject parsing(Map<String, Object> attributes) {
        return new JSONObject((Map) attributes.get(RESPONSE));
    }

    @Override
    public String getId() {
        return String.valueOf(attributes.get(ID));
    }

    @Override
    public String getNickName() {
        return (String) parsing(attributes).get(NAME);
    }

    @Override
    public String getEmail() {
        return (String) parsing(attributes).get(EMAIL);
    }
}