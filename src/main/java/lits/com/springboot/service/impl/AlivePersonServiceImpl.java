package lits.com.springboot.service.impl;

import lits.com.springboot.dto.PersonDto;
import lits.com.springboot.model.Person;
import lits.com.springboot.repository.PersonRepository;
import lits.com.springboot.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service("alivePersonService")
public class AlivePersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PersonDto getById(Long id) {
        PersonDto personDto = Optional.ofNullable(personRepository.findOne(id))
                .filter(e -> !e.getIsDead())
                .map(e -> modelMapper.map(e, PersonDto.class))
                .orElseGet(PersonDto::new);

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
                .filter(e -> !e.getIsDead())
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());



        return personDtos;
    }

    @Override
    public List<PersonDto> getAllPersonsByCity(Long cityId) {
        return personRepository.findAllByCityId(cityId).stream()
                .filter(Objects::nonNull)
                .filter(e -> !e.getIsDead())
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonDto> getAllPersonsByName(String name) {
        return personRepository.findAllByNameContains(name).stream()
                .filter(Objects::nonNull)
                .filter(e -> !e.getIsDead())
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto save(PersonDto personDto) {
//  todo implement logic with city
//      City city = person.getCity();
//      cityRepository.findByName(city.getName()) != null ?  :
        return Optional.ofNullable(personDto)
                .filter(e -> !e.getIsDead())
                .map(e -> modelMapper.map(e, Person.class))
                .map(e -> personRepository.save(e))
                .map(e -> modelMapper.map(e, PersonDto.class))
                .orElse(new PersonDto());
    }

    @Override
    public List<PersonDto> findByNameAndAge(String name, Integer age) {
        return personRepository.findByNameAndAge(name, age).stream()
                .filter(Objects::nonNull)
                .filter(e -> !e.getIsDead())
                .map(e -> modelMapper.map(e, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto update(PersonDto personDto) {
        return Optional.ofNullable(personDto)
                .filter(e -> !e.getIsDead())
                .map(e -> modelMapper.map(e, Person.class))
                .map(e -> personRepository.save(e))
                .map(e -> modelMapper.map(e, PersonDto.class))
                .orElse(new PersonDto());
    }
}
