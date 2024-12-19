package kitten.core.corecommon.model.provider;

import java.util.Map;

public record GoogleOauth2UserInfo(

        Attributes oauthAttributes

) implements Oauth2UserInfo {

    private static final String SUB = "sub";
    private static final String NAME = "name";
    private static final String EMAIL = "email";

    public GoogleOauth2UserInfo(Map<String, Object> attributes) {
        this(new Attributes(attributes));
    }

    public record Attributes(

            Map<String, Object> attributes
    ) {
    }

    @Override
    public String getId() {
        return (String) oauthAttributes.attributes.get(SUB);
    }

    @Override
    public String getNickName() {
        return (String) oauthAttributes.attributes.get(NAME);
    }

    @Override
    public String getEmail() {
        return (String) oauthAttributes.attributes.get(EMAIL);
    }
}
