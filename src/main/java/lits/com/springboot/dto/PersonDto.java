package lits.com.springboot.dto;

import lits.com.springboot.model.City;

public class PersonDto{

    private Integer id;

    private String name;

    private Integer age;

    private Boolean isDead;

    private Integer cityId;

    public PersonDto() {
    }

    public PersonDto(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public PersonDto(String name, Integer age, Boolean isDead, Integer cityId) {
        this.name = name;
        this.age = age;
        this.isDead = isDead;
        this.cityId = cityId;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getDead() {
        return isDead;
    }

    public void setDead(Boolean dead) {
        isDead = dead;
    }
}
