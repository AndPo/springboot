package lits.com.springboot.controller;

import lits.com.springboot.dto.PersonDto;
import lits.com.springboot.repository.CityRepository;
import lits.com.springboot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/api/persons")
public class PersonController {

    @Autowired
    private Map<String, PersonService> personServicesMap;

    @Autowired
    private CityRepository cityRepository;

    private PersonService qualifiedPersonService(Boolean isAlive){
        PersonService personService;
        if (isAlive == null){
            personService = personServicesMap.get("personService");
        }else{
            personService = isAlive ? personServicesMap.get("alivePersonService") : personServicesMap.get("deadPersonService");
        }
        return personService;
    }

    //TODO change methods. Methods must be sensitive to isDead field
    @GetMapping(value = "")
    public List<PersonDto> geAllPersons(@RequestParam(value = "isalive", required = false) Boolean isAlive){
        return qualifiedPersonService(isAlive).getAllPersons();
    }

    @GetMapping(value = "/{id}")
    public PersonDto getPersonById(@PathVariable("id")Long id) {

        return qualifiedPersonService(null).getById(id);
    }

    @PostMapping(value = "")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGE')")
    public PersonDto savePerson(@Validated @RequestBody PersonDto personDto){
        return qualifiedPersonService(null).save(personDto);
    }

    @PutMapping(value = "")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PersonDto updatePerson(@RequestBody PersonDto personDto){
        return qualifiedPersonService(null).update(personDto);
    }

    @GetMapping(value = "", params = "name")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGE') or hasRole('ROLE_USER')")
    public List<PersonDto> getAllPersonsByName(@RequestParam(value = "name", required = true, defaultValue = "") String name,
                                               @RequestParam(value = "age", required = false) Integer age,
                                               @RequestParam(value = "isalive", required = false) Boolean isAlive){
        return qualifiedPersonService(isAlive).getAllPersonsByName(name);
    }

    @GetMapping(value = "/city/{city_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGE') or hasRole('ROLE_USER')")
    public List<PersonDto> getAlivePersonsByCity(@PathVariable("city_id") Long cityId,
                                                 @RequestParam(value = "isalive", required = false) Boolean isAlive){
        return qualifiedPersonService(isAlive).getAllPersonsByCity(cityId);
    }
}
