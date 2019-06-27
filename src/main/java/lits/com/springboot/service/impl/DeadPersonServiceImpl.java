package lits.com.springboot.service.impl;

import lits.com.springboot.dto.PersonDto;
import lits.com.springboot.model.Person;
import lits.com.springboot.repository.CityRepository;
import lits.com.springboot.repository.PersonRepository;
import lits.com.springboot.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service("deadPersonService")
public class DeadPersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PersonDto getById(Long id) {
        PersonDto personDto = Optional.ofNullable(personRepository.findOne(id))
                .filter(Person::getIsDead)
                .map(e -> modelMapper.map(e, PersonDto.class))
                .orElse(new PersonDto());

        if (personDto.equals(new PersonDto())) {
            log.warn("Got null or empty Person Object from repository");
        } else {
            log.info("Got " + personDto + " Object from repository");
        }

        return personDto;
    }

    @Override
    public List<PersonDto> getAllPersons() {
        List<PersonDto> personDtos = personRepository.findAll().stream()
                .filter(Objects::nonNull)
                .filter(Person::getIsDead)
                .peek(e -> log.info("Got " + e + " attempting add to list" ))
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
                .filter(Objects::nonNull)
                .filter(Person::getIsDead)
                .peek(e -> log.info("Got " + e + " attempting add to list" ))
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
                .filter(Objects::nonNull)
                .filter(Person::getIsDead)
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

        if (personDto.equals(new PersonDto()) || personDto == null) {
            log.warn("Got null or empty PersonDto. Nothing to save");
        } else {
            log.info("Attempting to save " + personDto + " to repository");
        }

        PersonDto resultPersonDto = Optional.ofNullable(personDto)
                .filter(PersonDto::getIsDead)
                .map(e -> modelMapper.map(e, Person.class))
                .map(e -> personRepository.save(e))
                .map(e -> modelMapper.map(e, PersonDto.class))
                .orElse(new PersonDto());

        if (resultPersonDto.equals(new PersonDto())) {
            log.warn("Got null or empty Person Object from repository after saving it");
        } else {
            log.info("Got " + personDto + " Object from repository");
        }

        return resultPersonDto;
    }

    @Override
    public List<PersonDto> findByNameAndAge(String name, Integer age) {
        List<PersonDto> personDtos = personRepository.findByNameAndAge(name, age).stream()
                .filter(Objects::nonNull)
                .filter(Person::getIsDead)
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

        if (personDto.equals(new PersonDto()) || personDto == null) {
            log.warn("Got null or empty PersonDto. Nothing to save");
        } else {
            log.info("Attempting to save " + personDto + " to repository");
        }

        PersonDto resultPersonDto = Optional.ofNullable(personDto)
                .filter(PersonDto::getIsDead)
                .map(e -> modelMapper.map(e, Person.class))
                .map(e -> personRepository.save(e))
                .map(e -> modelMapper.map(e, PersonDto.class))
                .orElse(new PersonDto());

        if (resultPersonDto.equals(new PersonDto())) {
            log.warn("Got null or empty Person Object from repository after saving it");
        } else {
            log.info("Got " + personDto + " Object from repository");
        }

        return resultPersonDto;
    }
}

