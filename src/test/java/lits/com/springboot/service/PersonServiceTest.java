package lits.com.springboot.service;

import lits.com.springboot.dto.PersonDto;
import lits.com.springboot.model.Person;
import lits.com.springboot.repository.CityRepository;
import lits.com.springboot.repository.PersonRepository;
import lits.com.springboot.service.impl.AlivePersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    PersonService personService = new AlivePersonService();

    @Test
    public void shouldSavePerson() {
        PersonDto personDto = new PersonDto();
        personDto.setDead(false);
        Person person = new Person();
        person.setDead(false);
        person.setName("john");
        when(modelMapper.map(personDto, Person.class)).thenReturn(person);

        when(personRepository.save(person)).thenReturn(person);

        when(modelMapper.map(person, PersonDto.class)).thenReturn(personDto);

        assertNotNull(
                personService.save(personDto));
    }

    @Test
    public void shouldGetPerson(){
        Person person = new Person();
        person.setDead(false);
        when(personRepository.findOne( 1l)).thenReturn(person);
        personService.getById(1l);
        verify(personRepository, times(1)).findOne(1l);
    }


}
