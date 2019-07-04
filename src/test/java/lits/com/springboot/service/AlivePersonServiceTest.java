package lits.com.springboot.service;

import lits.com.springboot.dto.PersonDto;
import lits.com.springboot.model.Person;
import lits.com.springboot.repository.PersonRepository;
import lits.com.springboot.service.impl.AlivePersonServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AlivePersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    PersonService alivePersonService = new AlivePersonServiceImpl();

    @Test
    public void shouldGetOnePerson(){
        Person person = new Person();
        PersonDto personDto = new PersonDto();

        person.setIsDead(false);
        when(personRepository.findById(1L).get()).thenReturn(person);
        when(modelMapper.map(person, PersonDto.class)).thenReturn(personDto);

        assertEquals(personDto, alivePersonService.getById(1L));
        verify(personRepository).findById(1L).get();
    }

    @Test
    public void shouldGetAllPersons(){
        List<Person> persons = new ArrayList<>();
        List<PersonDto> personDtos = new ArrayList<>();

        Person person = new Person();
        PersonDto personDto = new PersonDto();

        persons.add(person);
        personDtos.add(personDto);
        person.setIsDead(false);

        when(personRepository.findAll()).thenReturn(persons);
        when(modelMapper.map(person, PersonDto.class)).thenReturn(personDto);

        assertEquals(personDtos, alivePersonService.getAllPersons());
        verify(personRepository).findAll();
    }

    @Test
    public void shouldGetAllPersonsByCity() {
        List<Person> persons = new ArrayList<>();
        List<PersonDto> personDtos = new ArrayList<>();

        Person person = new Person();
        PersonDto personDto = new PersonDto();

        persons.add(person);
        personDtos.add(personDto);
        person.setIsDead(false);

        when(personRepository.findAllByCityId(1l)).thenReturn(persons);
        when(modelMapper.map(person, PersonDto.class)).thenReturn(personDto);

        assertEquals(personDtos, alivePersonService.getAllPersonsByCity(1l));
        verify(personRepository).findAllByCityId(1l);
    }

    @Test
    public void shouldGetAllPersonsByName() {
        List<Person> persons = new ArrayList<>();
        List<PersonDto> personDtos = new ArrayList<>();

        Person person = new Person();
        PersonDto personDto = new PersonDto();

        persons.add(person);
        personDtos.add(personDto);
        person.setIsDead(false);

        when(personRepository.findAllByNameContains("Person")).thenReturn(persons);
        when(modelMapper.map(person, PersonDto.class)).thenReturn(personDto);

        assertEquals(personDtos, alivePersonService.getAllPersonsByName("Person"));
        verify(personRepository).findAllByNameContains("Person");
    }

    @Test
    public void shouldSavePerson() {
        PersonDto personDto = new PersonDto();
        Person person = new Person();

        personDto.setIsDead(false);

        when(modelMapper.map(personDto, Person.class)).thenReturn(person);
        when(personRepository.save(person)).thenReturn(person);
        when(modelMapper.map(person, PersonDto.class)).thenReturn(personDto);

        assertEquals(personDto, alivePersonService.save(personDto));
        verify(personRepository).save(person);
    }

    @Test
    public void shouldGetAllByNameAndAge() {
        List<Person> persons = new ArrayList<>();
        List<PersonDto> personDtos = new ArrayList<>();

        Person person = new Person();
        PersonDto personDto = new PersonDto();

        persons.add(person);
        personDtos.add(personDto);
        person.setIsDead(false);

        when(personRepository.findByNameAndAge("Person", 10)).thenReturn(persons);
        when(modelMapper.map(person, PersonDto.class)).thenReturn(personDto);

        assertEquals(personDtos, alivePersonService.findByNameAndAge("Person", 10));
        verify(personRepository).findByNameAndAge("Person", 10);
    }


    @Test
    public void shouldUpdatePerson() {
        PersonDto personDto = new PersonDto();
        Person person = new Person();

        personDto.setIsDead(false);

        when(personRepository.save(person)).thenReturn(person);
        when(modelMapper.map(personDto, Person.class)).thenReturn(person);
        when(modelMapper.map(person, PersonDto.class)).thenReturn(personDto);

        assertEquals(personDto, alivePersonService.save(personDto));
        verify(personRepository).save(person);
    }
}
