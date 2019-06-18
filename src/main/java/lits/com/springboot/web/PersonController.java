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
    private PersonService alivePersonService;

    @Autowired
    @Qualifier(value = "dead")
    private PersonService deadPersonService;


    @GetMapping(value = "/persons", produces = "application/json;  charset=UTF-8")
    public List<PersonDto> geAllPersons(){
        List<PersonDto> personDtos = alivePersonService.getAllPersons();
        return personDtos;
    }

    @PostMapping(value = "/person/save")
    public Person savePerson(@RequestBody PersonDto personDto){
        return alivePersonService.save(personDto);
    }

    @GetMapping(value = "/person")
    public PersonDto getPersonById(@RequestParam Integer id) {
        return alivePersonService.getById(id);
    }

    @GetMapping(value = "/persons/alive")
    public List<PersonDto> getAlivePersons(@RequestParam(value = "name", required = true) String name){
        return alivePersonService.getAllPersonsByName(name);
    }

    @GetMapping(value = "/persons/dead")
    public List<PersonDto> getDeadPersons(@RequestParam(value = "name", required = true) String name){
        return deadPersonService.getAllPersonsByName(name);
    }

    @GetMapping(value = "/persons")
    public List<PersonDto> getPersonByNameAndByAge(@RequestParam(value = "name", required = true) String name,
                                                @RequestParam(value = "age", required = true) Integer age)
    {
        return alivePersonService.findByNameAndAge(name, age);
    }


}