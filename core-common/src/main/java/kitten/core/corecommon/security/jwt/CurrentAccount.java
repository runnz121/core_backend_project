package kitten.core.corecommon.security.jwt;

import kitten.core.coredomain.model.AuthRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
@RequestScope
public class CurrentAccount {

    private String userEmail;

    private AuthRoles authRoles;
}