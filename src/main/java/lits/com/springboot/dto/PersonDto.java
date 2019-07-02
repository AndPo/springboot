package lits.com.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto{

    private String id;

    private String name;

    private Integer age;

    private Boolean isDead;

    private CityDto city;
}
