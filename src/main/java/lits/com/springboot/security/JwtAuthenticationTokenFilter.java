package lits.com.springboot.security;

import lits.com.springboot.service.TokenService;
import lits.com.springboot.service.UserService;
import lits.com.springboot.service.impl.UserServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(this.getClass());

    private static final String BEARER_TYPE = "Bearer";

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        Long accountId =  Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION)).filter(this::containsBearerToken)
                .map(token -> token.substring(BEARER_TYPE.length() + 1)).map(token -> tokenService.parseToken(token)).orElse(null);

        logger.info("checking authentication for user " + accountId);

        if (accountId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            JwtUser jwtUser = JwtUserFactory.create(accountId, userService.getAuthority(userService.findById(accountId)));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtUser, null, jwtUser.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            logger.info("authenticated user " + accountId + ", setting security context");
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
    private boolean containsBearerToken(String authHeader) {
        return Optional.ofNullable(authHeader).filter(e -> e.startsWith(BEARER_TYPE))
                .filter(e -> e.length() > BEARER_TYPE.length() + 1).isPresent();
    }

}
