package lits.com.springboot.repository;

import lits.com.springboot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

   List<Person> findByNameAndAge(String name, Integer age);
}
