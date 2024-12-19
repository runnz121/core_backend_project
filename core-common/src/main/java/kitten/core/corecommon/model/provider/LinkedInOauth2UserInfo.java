package kitten.core.corecommon.model.provider;

import java.util.Map;

public record LinkedInOauth2UserInfo(

        Map<String, Object> attributes

) implements Oauth2UserInfo {

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getNickName() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }
}
