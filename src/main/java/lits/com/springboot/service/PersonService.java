package lits.com.springboot.service;

import lits.com.springboot.dto.PersonDto;
import lits.com.springboot.model.Person;

import java.util.List;

public interface PersonService {

    PersonDto getById(Long id);

    List<PersonDto> getAllPersons();

    List<PersonDto> getAllPersonsByCity(Long cityId);

    public List<PersonDto> getAllPersonsByName(String name);

    PersonDto save(PersonDto personDto);

    List<PersonDto> findByNameAndAge(String name, Integer age);

    PersonDto update(PersonDto personDto);
}
