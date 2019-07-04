package lits.com.springboot.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PersonDto {

    private String id;

    @NotEmpty
    @Size(max = 32)
    private String name;

    @Min(value = 0, message = "value must be greater then 0")
    @Max(value = 110, message = "value must be less then 110")
    private Integer age;

    private Boolean isDead;

    private CityDto city;
}
