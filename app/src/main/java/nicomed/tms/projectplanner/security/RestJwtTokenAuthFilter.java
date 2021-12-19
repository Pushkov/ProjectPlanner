package nicomed.tms.projectplanner.security;

import nicomed.tms.projectplanner.security.jwt.JwtTokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class RestJwtTokenAuthFilter extends RestAuthFilter {

    JwtTokenProvider jwtTokenProvider;

    public RestJwtTokenAuthFilter(RequestMatcher requiresAuthenticationRequestMatcher, JwtTokenProvider jwtTokenProvider) {
        super(requiresAuthenticationRequestMatcher);
        this.jwtTokenProvider = jwtTokenProvider;
    }

    protected String getPassword(HttpServletRequest httpServletRequest) {
        return null;
    }

    protected String getUsername(HttpServletRequest httpServletRequest) {
        return null;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }
}
