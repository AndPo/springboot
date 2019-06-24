package lits.com.springboot.service;

import lits.com.springboot.dto.PersonDto;
import lits.com.springboot.model.Person;
import lits.com.springboot.repository.CityRepository;
import lits.com.springboot.repository.PersonRepository;
import lits.com.springboot.service.impl.PersonServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    PersonService personService = new PersonServiceImpl();

    @Test
    public void shouldGetOnePerson(){
        Person person = new Person();
        person.setDead(false);
        when(personRepository.findOne( 1l)).thenReturn(person);
        personService.getById(1l);
        verify(personRepository, times(1)).findOne(1l);
    }

    @Test
    public void shouldGetAllPersons(){
        List<Person> persons = new ArrayList<>();
        List<PersonDto> personDtos = new ArrayList<>();

        Person person = new Person();
        PersonDto personDto = new PersonDto();

        persons.add(person);
        personDtos.add(personDto);

        when(personRepository.findAll()).thenReturn(persons);
        when(modelMapper.map(person, PersonDto.class)).thenReturn(personDto);

        assertEquals(personDtos, personService.getAllPersons());
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

        when(personRepository.findAllByCityId(1l)).thenReturn(persons);
        when(modelMapper.map(person, PersonDto.class)).thenReturn(personDto);

        assertEquals(personDtos, personService.getAllPersonsByCity(1l));
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

        when(personRepository.findAllByNameContains("Person")).thenReturn(persons);
        when(modelMapper.map(person, PersonDto.class)).thenReturn(personDto);

        assertEquals(personDtos, personService.getAllPersonsByName("Person"));
        verify(personRepository).findAllByNameContains("Person");
    }

    @Test
    public void shouldSavePerson() {
        PersonDto personDto = new PersonDto();
        Person person = new Person();

        when(modelMapper.map(personDto, Person.class)).thenReturn(person);
        when(personRepository.save(person)).thenReturn(person);
        when(modelMapper.map(person, PersonDto.class)).thenReturn(personDto);

        assertEquals(personDto, personService.save(personDto));
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

        when(personRepository.findByNameAndAge("Person", 10)).thenReturn(persons);
        when(modelMapper.map(person, PersonDto.class)).thenReturn(personDto);

        assertEquals(personDtos, personService.findByNameAndAge("Person", 10));
        verify(personRepository).findByNameAndAge("Person", 10);
    }


    @Test
    public void shouldUpdatePerson() {
        PersonDto personDto = new PersonDto();
        Person person = new Person();

        when(personRepository.save(person)).thenReturn(person);
        when(modelMapper.map(personDto, Person.class)).thenReturn(person);
        when(modelMapper.map(person, PersonDto.class)).thenReturn(personDto);

        assertEquals(personDto, personService.save(personDto));
        verify(personRepository).save(person);
    }
}
