package lits.com.springboot.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    //TODO find problem with save isDead field to database
    private Boolean isDead;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public Person setId(Long id) {
        this.id = id;
        return this;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Person setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Person setDead(Boolean dead) {
        isDead = dead;
        return this;
    }

    public Person setCity(City city) {
        this.city = city;
        return this;
    }
}
