package kitten.core.corecommon.model;


import kitten.core.corecommon.model.provider.Oauth2UserInfo;
import kitten.core.coredomain.model.AuthRoles;
import kitten.core.coredomain.user.entity.Users;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Builder
public record Oauth2UserDetail(

        String id,

        String email,

        AuthRoles role,

        OauthProvider provider,

        Map<String, Object> oauthAttribute,

        Collection<GrantedAuthority> authorities

) implements OAuth2User, UserDetails {

    public static Oauth2UserDetail ofUser(Oauth2UserInfo info,
                                          OauthProvider provider) {
        return Oauth2UserDetail.builder()
            .id(info.getId())
            .provider(provider)
            .email(info.getEmail())
            .role(AuthRoles.ANONYMOUS) // 현재는 기본 유저 상태
            .build();
    }

    public static Oauth2UserDetail ofToken(Users users) {
        return Oauth2UserDetail.builder()
                .email(users.getEmail())
                .role(users.getAuthRoles())
                .authorities(createAuthorities(users.getAuthRoles().getRoleValue()))
                .build();
    }

    public static Oauth2UserDetail ofAnonymous() {
        return Oauth2UserDetail.builder()
                .email("anonymous@kitten.com")
                .role(AuthRoles.ANONYMOUS)
                .authorities(createAuthorities(AuthRoles.ANONYMOUS.getRoleValue()))
                .build();
    }

    public static Collection<GrantedAuthority> createAuthorities(String roles){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(roles));
        return authorities;
    }

    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauthAttribute;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return id;
    }
}
