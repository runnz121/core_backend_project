package kitten.core.coredomain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum AuthRoles {

    USER("user", AuthRoles.ROLE_USER),
    ADMIN("admin", AuthRoles.ROLE_ADMIN),
    ANONYMOUS("anonymous", AuthRoles.ROLE_ANONYMOUS)
    ;

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    public static final List<String> ROLES = List.of(ROLE_USER, ROLE_ADMIN, ROLE_ANONYMOUS);

    private final String roleName;
    private final String roleValue;
}
