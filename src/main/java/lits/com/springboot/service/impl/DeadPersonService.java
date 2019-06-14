package lits.com.springboot.service.impl;

import lits.com.springboot.dto.PersonDto;
import lits.com.springboot.model.Person;
import lits.com.springboot.repository.PersonRepository;
import lits.com.springboot.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("dead")
public class DeadPersonService implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PersonDto getById(Integer id) {
        Person person = personRepository.findOne(id);
        person = person.getDead() ? person : null;
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public List<PersonDto> getAllPersons() {
        List<PersonDto> personDtos = new ArrayList<>();
        List<Person> persons = personRepository.findAll();
        for (Person person: persons) {
            person = person.getDead() ? person : null;
            personDtos.add(modelMapper.map(person, PersonDto.class));
        }
        return personDtos;
    }

    @Override
    public Person save(PersonDto personDto) {
        return personRepository.save(modelMapper.map(personDto, Person.class));
    }

    @Override
    public List<PersonDto> findByNameAndAge(String name, Integer age) {
        List<PersonDto> personDtos = new ArrayList<>();
        List<Person> persons = personRepository.findByNameAndAge(name, age);
        for (Person person: persons) {
            person = person.getDead() ? person : null;
            personDtos.add(modelMapper.map(person, PersonDto.class));
        }
        return personDtos;
    }
}

