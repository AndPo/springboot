package lits.com.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto{

    private String id;
    @NotNull
    private String name;

    @Min(value = 10)
    @Max(value =  25)
    private Integer age;

    private Boolean isDead;

    private CityDto city;
}
