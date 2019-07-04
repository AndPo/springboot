package lits.com.springboot.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class UserDto {

    //Todo ask teachere about diff
    // @Email
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$" )
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$" )
    private String password;
}
