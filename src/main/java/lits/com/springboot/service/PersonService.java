package lits.com.springboot.service;

import lits.com.springboot.dto.PersonDto;
import lits.com.springboot.model.Person;

import java.util.List;

public interface PersonService {

    PersonDto getById(Integer id);

    List<PersonDto> getAllPersons();

    Person save(PersonDto personDto);

    List<PersonDto> findByNameAndAge(String name, Integer age);

}
