package lits.com.springboot.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection = "pet")
public class Pet {
    @Id
    private String id;

    private String name;

    private String type;

    @DBRef
    private Person owner;
}
