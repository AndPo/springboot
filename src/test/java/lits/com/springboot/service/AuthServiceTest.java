package lits.com.springboot.service;

import lits.com.springboot.model.User;
import lits.com.springboot.repository.UserRepository;
import lits.com.springboot.service.impl.AuthServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService = new AuthServiceImpl();

    @Test
    public void shouldAuth() {
        User user  = new User();
        user.setId(1L);

        when(userRepository.findOneByEmail("login").get()).thenReturn(user);
        when(tokenService.createToken(user.getId())).thenReturn("BarrerToken");

        assertEquals("BarrerToken", authService.auth("login", "pass"));
    }
}
