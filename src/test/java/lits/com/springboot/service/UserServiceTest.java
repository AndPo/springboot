package lits.com.springboot.service;

import lits.com.springboot.repository.UserRepository;
import lits.com.springboot.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    UserService userService = new UserServiceImpl();

    @Test
    public void shouldLoadUserByUserName() {

    }

    @Test
    public void shouldGetAuthotity() {

    }

}
