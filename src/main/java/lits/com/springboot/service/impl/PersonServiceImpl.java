package lits.com.springboot.service.impl;

import lits.com.springboot.dto.PersonDto;
import lits.com.springboot.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "personService")
public class PersonServiceImpl implements PersonService {
    @Override
    public PersonDto getById(Integer id) {
        return null;
    }

    @Override
    public List<PersonDto> getAllPersons() {
        return null;
    }

    @Override
    public List<PersonDto> getAllPersonsByCity(Integer cityId) {
        return null;
    }

    @Override
    public List<PersonDto> getAllPersonsByName(String name) {
        return null;
    }

    @Override
    public PersonDto save(PersonDto personDto) {
        return null;
    }

    @Override
    public List<PersonDto> findByNameAndAge(String name, Integer age) {
        return null;
    }

    @Override
    public PersonDto update(PersonDto personDto) {
        return null;
    }
}
