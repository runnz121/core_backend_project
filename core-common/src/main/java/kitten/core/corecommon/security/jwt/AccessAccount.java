package kitten.core.corecommon.security.jwt;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.TYPE})
@AuthenticationPrincipal(expression =
        " T(kitten.core.corecommon.model.Oauth2UserDetail).isInstance(#this) "
                + " ? new kitten.core.corecommon.security.jwt.CurrentAccount(#this.email(), #this.role()) "
                + " : null ")
public @interface AccessAccount {
}
