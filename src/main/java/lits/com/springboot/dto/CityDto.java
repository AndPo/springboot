package lits.com.springboot.dto;

import lits.com.springboot.model.Person;

import javax.persistence.*;
import java.util.List;

public class CityDto {

    private Integer id;

    private String name;

    private List<Person> persons;

    public CityDto() {
    }

    public CityDto(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
