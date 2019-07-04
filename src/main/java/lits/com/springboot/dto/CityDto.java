package lits.com.springboot.dto;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CityDto {

    private Integer id;

    @NotEmpty
    @Size(max = 32, message = "Size must be less then 32 letters")
    private String name;

    @Size(max = 255, message = "sixe must be less then 255 letters")
    private String description;

}
