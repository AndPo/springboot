package lits.com.springboot.service.impl;

import lits.com.springboot.exception.UserNotFoundException;
import lits.com.springboot.model.User;
import lits.com.springboot.repository.UserRepository;
import lits.com.springboot.service.AuthService;
import lits.com.springboot.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String auth(String login, String pass) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login,
                        pass
                )
        );

        log.info("Attempting create token for user " + login);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findOneByEmail(login)
                .orElseThrow(() -> new UserNotFoundException("User with login " + login + " not found"));

        return tokenService.createToken(user.getId());
    }
}
