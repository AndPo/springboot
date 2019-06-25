package lits.com.springboot.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    public Role setId(Long id) {
        this.id = id;
        return this;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }

    public Role setDescription(String description) {
        this.description = description;
        return this;
    }
}
