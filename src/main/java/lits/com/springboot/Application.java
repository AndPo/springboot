package lits.com.springboot;

import lits.com.springboot.model.Role;
import lits.com.springboot.model.User;
import lits.com.springboot.repository.RoleRepository;
import lits.com.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application implements ApplicationRunner {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String email = "serhiiilnytskyi@gmail.com";
		String password = passwordEncoder.encode("F,hfrflf,hf314");
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		Role role = new Role();
		role.setName("ADMIN");
		role.setDescription("Some admin role");
		role.setUserId(user.getId());
		roleRepository.save(role);
		List<Role> roles= new ArrayList<Role>();
		roles.add(role);
		user.setRoles(roles);
		userRepository.save(user);
	}
}
