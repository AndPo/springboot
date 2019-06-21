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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

		Role role = new Role()
				.setName("ADMIN")
				.setDescription("Some admin role");

		User user = new User()
				.setEmail(email)
				.setPassword(password)
				.setRoles(new HashSet<Role>(){{add(role);}});

//		roleRepository.save(role);
		userRepository.save(user);
	}
}
