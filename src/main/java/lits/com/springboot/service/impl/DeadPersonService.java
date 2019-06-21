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

@Service("deadPersonService")
public class DeadPersonService implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PersonDto getById(Long id) {
        Person person = personRepository.findOne(id);
        person = person.getDead() ? person : null;
        return modelMapper.map(person, PersonDto.class);
    }


    @Override
    public List<PersonDto> getAllPersonsByCity(Long cityId) {
        List<PersonDto> personDtos = new ArrayList<>();
        List<Person> persons = personRepository.findByCityId(cityId);
        for (Person person : persons) {
            person = person.getDead() ? person: null;
            personDtos.add(modelMapper.map(person, PersonDto.class));
        }
        return personDtos;
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
    public List<PersonDto> getAllPersonsByName(String name) {
        List<PersonDto> personDtos = new ArrayList<>();
        List<Person> persons = personRepository.findAllByNameContains(name);
        for (Person person : persons) {
            person = person.getDead() ? person : null;
            personDtos.add(modelMapper.map(person, PersonDto.class));
        }
        return personDtos;
    }

    @Override
    public PersonDto save(PersonDto personDto) {
        return modelMapper.map(personRepository.save(modelMapper.map(personDto, Person.class)), PersonDto.class);
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

    @Override
    public PersonDto update(PersonDto personDto) {
        return null;
    }
}

