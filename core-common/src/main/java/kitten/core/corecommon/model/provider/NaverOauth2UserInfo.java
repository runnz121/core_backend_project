package kitten.core.corecommon.model.provider;

import net.minidev.json.JSONObject;

import java.util.Map;

public record NaverOauth2UserInfo(

        Map<String, Object> attributes

) implements Oauth2UserInfo {

    private final static String RESPONSE = "response";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";

    public JSONObject parsing(Map<String, Object> attributes) {
        return new JSONObject((Map) attributes.get(RESPONSE));
    }

    @Override
    public String getId() {
        return (String) parsing(attributes).get(ID);
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
