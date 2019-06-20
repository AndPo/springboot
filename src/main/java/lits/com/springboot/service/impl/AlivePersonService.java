package lits.com.springboot.service.impl;

import lits.com.springboot.dto.PersonDto;
import lits.com.springboot.model.Person;
import lits.com.springboot.repository.CityRepository;
import lits.com.springboot.repository.PersonRepository;
import lits.com.springboot.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("alivePersonService")
public class AlivePersonService implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PersonDto getById(Integer id) {
        Person person = personRepository.findOne(id);
        person = person.getDead() ? null : person;
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public List<PersonDto> getAllPersons() {
        List<PersonDto> personDtos = new ArrayList<>();
        List<Person> persons = personRepository.findAll();
        for (Person person : persons) {
            person = person.getDead() ? null : person;
            personDtos.add(modelMapper.map(person, PersonDto.class));
        }
        return personDtos;
    }

    @Override
    public List<PersonDto> getAllPersonsByCity(Integer cityId) {
        List<PersonDto> personDtos = new ArrayList<>();
        List<Person> persons = personRepository.findByCityId(cityId);
        for (Person person : persons) {
            person = person.getDead() ? null : person;
            personDtos.add(modelMapper.map(person, PersonDto.class));
        }
        return personDtos;
    }

    @Override
    public List<PersonDto> getAllPersonsByName(String name) {
        List<PersonDto> personDtos = new ArrayList<>();
        List<Person> persons = personRepository.findAllByNameContains(name);
        for (Person person : persons) {
            person = person.getDead() ? null : person;
            personDtos.add(modelMapper.map(person, PersonDto.class));
        }
        return personDtos;
    }

    @Override
    public PersonDto save(PersonDto personDto) {
        personDto.setDead(false);
        personDto = personDto.getDead() ? null : personDto;
        Person person = modelMapper.map(personDto, Person.class);
       /// person.setCity(cityRepository.findById(personDto.getCityId()).getId());
        return modelMapper.map(personRepository.save(person), PersonDto.class);
    }

    @Override
    public List<PersonDto> findByNameAndAge(String name, Integer age) {
        List<PersonDto> personDtos = new ArrayList<>();
        List<Person> persons = personRepository.findByNameAndAge(name, age);
        for (Person person : persons) {
            person = person.getDead() ? null : person;
            personDtos.add(modelMapper.map(person, PersonDto.class));
        }
        return personDtos;
    }

    @Override
    public PersonDto update(PersonDto personDto) {
        Person person = modelMapper.map(personDto, Person.class);
        return modelMapper.map(personRepository.save(person), PersonDto.class);
    }
}
