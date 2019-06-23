package lits.com.springboot.model;

import javax.persistence.*;

@Entity
@Table
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    //TODO find problem with save isDead field to database
    private Boolean isDead;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public Long getId() {
        return id;
    }

    public Person setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Person setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Boolean getDead() {
        return isDead;
    }

    public Person setDead(Boolean dead) {
        isDead = dead;
        return this;
    }

    public City getCity() {
        return city;
    }

    public Person setCity(City city) {
        this.city = city;
        return this;
    }
}
