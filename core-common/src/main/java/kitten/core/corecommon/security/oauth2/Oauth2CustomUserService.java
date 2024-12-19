package kitten.core.corecommon.security.oauth2;

import kitten.core.corecommon.model.Oauth2UserDetail;
import kitten.core.corecommon.model.OauthProvider;
import kitten.core.corecommon.model.provider.Oauth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Oauth2CustomUserService extends DefaultOAuth2UserService {

    private final ApplicationEventPublisher publisher;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        return processOauth2User(userRequest, oAuth2User);
    }

    private OAuth2User processOauth2User(OAuth2UserRequest userRequest,
                                         OAuth2User oAuth2User){
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OauthProvider provider = OauthProvider.getProvider(registrationId);
        Oauth2UserInfo oauth2UserInfo = provider.toOauthProvide(oAuth2User.getAttributes());
        Oauth2UserDetail oauth2UserDetail = Oauth2UserDetail.ofUser(oauth2UserInfo, provider);
        publisher.publishEvent(oauth2UserDetail);
        return oauth2UserDetail;
    }
}
