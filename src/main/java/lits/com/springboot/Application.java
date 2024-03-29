package lits.com.springboot;

import lits.com.springboot.model.Person;
import lits.com.springboot.model.Pet;
import lits.com.springboot.model.Role;
import lits.com.springboot.model.User;
import lits.com.springboot.repository.PersonRepository;
import lits.com.springboot.repository.PetRepository;
import lits.com.springboot.repository.RoleRepository;
import lits.com.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;

@EnableSwagger2
@SpringBootApplication
public class Application implements ApplicationRunner {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PetRepository petRepository;

	@Autowired
	PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String email = "serhiiilnytskyi@gmail.com";
		String password = passwordEncoder.encode("F,hfrflf,hf314");

		Role role = new Role();
				role.setName("ADMIN");
				role.setDescription("Some admin role");

		User user = User.builder()
				.email(email)
				.password(password)
				.roles(new HashSet<Role>(){{add(role);}})
				.build();

		//roleRepository.save(role);
		//userRepository.save(user);

//		Person person = Person.builder().name("HappyMan").age(21).isDead(false).build();
//		personRepository.save(person);
//
//		Pet pet = new Pet();
//		pet.setName("Gav");
//		pet.setOwner(person);
//		pet.setType("Dog");
//
//		petRepository.save(pet);
	}
}
