package kitten.core.corecommon.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import kitten.core.corecommon.properties.OauthProperties;
import kitten.core.corecommon.utils.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final OauthProperties properties;

    public String generateJwt(String roleName,
                              String userEmail) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + AuthUtil.NOT_EXPIRED);
        return Jwts.builder()
                .issuedAt(now)
                .expiration(expiry)
                .issuer(AuthUtil.ISSUER)
                .subject(AuthUtil.TOKEN_SUBJECT)
                .claim(AuthUtil.USER_EMAIL, userEmail)
                .claim(AuthUtil.ROLE, roleName)
                .signWith(getSigningKey())
                .compact();
    }

    public String getUserEmail(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
        return (String) claimsJws.getPayload().get(AuthUtil.USER_EMAIL);
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(properties.getJwtSigningKey().getBytes(StandardCharsets.UTF_8));
    }
}
