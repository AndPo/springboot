package lits.com.springboot.service;

import lits.com.springboot.model.Role;
import lits.com.springboot.model.User;
import lits.com.springboot.repository.UserRepository;
import lits.com.springboot.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService = new UserServiceImpl();

    @Test(expected = UsernameNotFoundException.class)
    public void shouldLoadUserByUserName() {
        User user = new User();
        user.setEmail("UserName");
        user.setPassword("Pass");

        when(userRepository.findOneByEmail("UserName")).thenReturn(user);

        assertNotNull(userService.loadUserByUsername("UserName"));
        verify(userRepository).findOneByEmail("UserName");

        userService.loadUserByUsername("UserName2");
    }

    @Test
    public void shouldGetAuthotity() {
        User user = new User();
        Set<Role> roles = new HashSet<>();
        List<GrantedAuthority> authorities = new ArrayList<>();

        Role role = new Role();
        role.setName("ROLE");
        roles.add(role);

        user.setRoles(roles);
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));

        assertEquals(authorities, userService.getAuthority(user));
    }

    @Test
    public void shouldGetUserById() {
        User user = new User();

        when(userRepository.findById(1L)).thenReturn(user);

        assertEquals(user, userService.findById(1L));
        verify(userRepository).findById(1L);
    }

}
