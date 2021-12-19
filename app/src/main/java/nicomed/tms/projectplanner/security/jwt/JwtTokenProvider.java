package nicomed.tms.projectplanner.security.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.Permission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

//    @Qualifier("jwtUserService")
    private final UserDetailsService userDetailsService;

    @Value("${jwt.token.secret}")
    private String secretKey;
    @Value("${jwt.token.header}")
    private String authorizationHeader;
    @Value("${jwt.token.expiration}")
    private long validityInMilliseconds;

    public String createToken(String username, List<Permission> permissions) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", getRoleNames(permissions));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserName(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public List<String> getRoleNames(List<Permission> permissions) {
        return permissions.stream()
                .map(Permission::getName)
                .collect(Collectors.toList());
    }
}
