package lits.com.springboot.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PetDto {

    private String id;

    @NotEmpty
    @Size(max = 32)
    private String name;

    //Todo create enum or entity with pets types
    private String type;

    private PersonDto owner;
}
