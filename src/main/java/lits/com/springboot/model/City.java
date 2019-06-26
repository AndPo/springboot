package lits.com.springboot.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"id"})
@Entity
@Table
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = CascadeType.ALL)
    private List<Person> persons;

    public City setId(Long id) {
        this.id = id;
        return this;
    }

    public City setName(String name) {
        this.name = name;
        return this;
    }

    public City setDescription(String description) {
        this.description = description;
        return this;
    }

    public City setPersons(List<Person> persons) {
        this.persons = persons;
        return this;
    }
}
