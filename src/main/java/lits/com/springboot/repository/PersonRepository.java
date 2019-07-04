package lits.com.springboot.repository;

import lits.com.springboot.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

   List<Person> findByNameAndAge(String name, Integer age);

   List<Person> findAllByNameContains(String name);

   List<Person> findAllByCityId(Long cityId);

//   List<Person> findAllByAgeBetweenAndNameContaining(Integer ageFrom, Integer ageTo, String name);
}
