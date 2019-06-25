package lits.com.springboot.service.impl;

import lits.com.springboot.dto.PersonDto;
import lits.com.springboot.model.Person;
import lits.com.springboot.repository.CityRepository;
import lits.com.springboot.repository.PersonRepository;
import lits.com.springboot.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("deadPersonService")
public class DeadPersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PersonDto getById(Long id) {
        return Optional.ofNullable(personRepository.findOne(id))
                .filter(Person::getIsDead)
                .map(e -> modelMapper.map(e, PersonDto.class))
                .orElse(new PersonDto());
    }

    @Override
    public List<PersonDto> getAllPersons() {
        return personRepository.findAll().stream()
                .filter(Person::getIsDead)
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonDto> getAllPersonsByCity(Long cityId) {
        return personRepository.findAllByCityId(cityId).stream()
                .filter(Person::getIsDead)
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonDto> getAllPersonsByName(String name) {
        return personRepository.findAllByNameContains(name).stream()
                .filter(Person::getIsDead)
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto save(PersonDto personDto) {
//        City city = person.getCity();
//        cityRepository.findByName(city.getName()) != null ?  :
        return Optional.ofNullable(personDto)
                .filter(PersonDto::getDead)
                .map(e -> modelMapper.map(e, Person.class))
                .map(e -> personRepository.save(e))
                .map(e -> modelMapper.map(e, PersonDto.class))
                .orElse(new PersonDto());
    }

    @Override
    public List<PersonDto> findByNameAndAge(String name, Integer age) {
        return personRepository.findByNameAndAge(name, age).stream()
                .filter(Person::getIsDead)
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto update(PersonDto personDto) {
        return Optional.ofNullable(personDto)
                .filter(PersonDto::getDead)
                .map(e -> modelMapper.map(e, Person.class))
                .map(e -> personRepository.save(e))
                .map(e -> modelMapper.map(e, PersonDto.class))
                .orElse(new PersonDto());
    }
}

