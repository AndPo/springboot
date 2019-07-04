package lits.com.springboot.service.impl;

import lits.com.springboot.dto.PersonDto;
import lits.com.springboot.exception.PersonNotFoundException;
import lits.com.springboot.model.Person;
import lits.com.springboot.repository.PersonRepository;
import lits.com.springboot.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service("alivePersonService")
public class AlivePersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PersonDto getById(Long id) {
        return personRepository.findById(id)
                .filter(e -> !e.getIsDead())
                .map(e -> modelMapper.map(e, PersonDto.class))
                .orElseThrow(() -> new PersonNotFoundException("Person with this id not found"));
    }

    @Override
    public List<PersonDto> getAllPersons() {
        List<PersonDto> personDtos = StreamSupport.stream(personRepository.findAll().spliterator(), false)
                .peek(e -> {
                    if (e == null)
                        throw new PersonNotFoundException("Person not found");
                })
                .filter(e -> !e.getIsDead())
                .peek(e -> log.info("Got " + e + " attempting add to list"))
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());

        if (personDtos.equals(new ArrayList<PersonDto>())) {
            log.warn("Got empty list of Person Objects from repository");
        }
        return personDtos;
    }

    @Override
    public List<PersonDto> getAllPersonsByCity(Long cityId) {
        List<PersonDto> personDtos = personRepository.findAllByCityId(cityId).stream()
                // todo flatMap(e -> e.orElseThrow(() -> new UserNotFoundException("546")))
                .peek(e -> {
                    if (e == null)
                        throw new PersonNotFoundException("Person is null");
                })
                .filter(e -> !e.getIsDead())
                .peek(e -> log.info("Got " + e + " attempting add to list"))
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());

        if (personDtos.equals(new ArrayList<PersonDto>())) {
            log.warn("Got empty list of Person Objects from repository");
        }

        return personDtos;
    }

    @Override
    public List<PersonDto> getAllPersonsByName(String name) {
        List<PersonDto> personDtos = personRepository.findAllByNameContains(name).stream()
                .peek(e -> {
                    if (e == null)
                        throw new PersonNotFoundException("Person is null");
                })
                .filter(e -> !e.getIsDead())
                .peek(e -> log.info("Got " + e + " attempting add to list"))
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());

        if (personDtos.equals(new ArrayList<PersonDto>())) {
            log.warn("Got empty list of Person Objects from repository");
        }

        return personDtos;
    }

    @Override
    public PersonDto save(PersonDto personDto) {
//  todo implement logic with city
//      City city = person.getCity();
//      cityRepository.findByName(city.getName()) != null ?  :

        PersonDto resultPersonDto = Optional.ofNullable(personDto)
                .map(e -> modelMapper.map(e, Person.class))
                .filter(e -> !e.getIsDead())
                .map(e -> personRepository.save(e))
                .map(e -> modelMapper.map(e, PersonDto.class))
                .orElseThrow(() -> new PersonNotFoundException("PersonDto is null"));

        return resultPersonDto;
    }

    @Override
    public List<PersonDto> findByNameAndAge(String name, Integer age) {
        List<PersonDto> personDtos = personRepository.findByNameAndAge(name, age).stream()
                .peek(e -> {
                    if (e == null)
                        throw new PersonNotFoundException("Person is null");
                })
                .filter(e -> !e.getIsDead())
                .peek(e -> log.info("Got " + e + " attempting add to list"))
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());

        if (personDtos.equals(new ArrayList<PersonDto>())) {
            log.warn("Got empty list of Person Objects from repository");
        }

        return personDtos;
    }

    @Override
    public PersonDto update(PersonDto personDto) {

        PersonDto resultPersonDto = Optional.ofNullable(personDto)
                .map(e -> modelMapper.map(e, Person.class))
                .filter(e -> !e.getIsDead())
                .map(e -> personRepository.save(e))
                .map(e -> modelMapper.map(e, PersonDto.class))
                .orElseThrow(() -> new PersonNotFoundException("PersonDto is null"));

        return resultPersonDto;


    }
}
