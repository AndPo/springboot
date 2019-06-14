package lits.com.springboot.web;

import lits.com.springboot.dto.PersonDto;
import lits.com.springboot.model.Person;
import lits.com.springboot.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/api")
public class PersonController {

    @Autowired
    @Qualifier(value = "alive")
    private PersonService personService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "users", produces = "application/json;  charset=UTF-8")
    public List<PersonDto> getPersonById(){
        List<PersonDto> personDtos = personService.getAllPersons();
        return personDtos;
    }

    @PostMapping
    public Person savePerson(@RequestBody PersonDto personDto){
        return personService.save(personDto);
    }

    @GetMapping(value = "/user")
    public PersonDto getPersonById(@RequestParam Integer id) {
        return modelMapper.map(personService.getById(id), PersonDto.class);
    }

    @GetMapping(value = "/users")
    public List<PersonDto> getPersonByNameAndByAge(@RequestParam(value = "name", required = true) String name,
                                                @RequestParam(value = "age", required = true) Integer age)
    {
        return personService.findByNameAndAge(name, age);
    }
}