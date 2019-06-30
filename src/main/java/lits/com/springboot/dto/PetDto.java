package lits.com.springboot.dto;

import lits.com.springboot.model.Person;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class PetDto {

    private String id;

    private String name;

    private String type;

    @DBRef
    private Person owner;
}
