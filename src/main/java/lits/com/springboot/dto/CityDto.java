package lits.com.springboot.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {

    private Integer id;

    @NotNull
    private String name;

    private String description;

}
