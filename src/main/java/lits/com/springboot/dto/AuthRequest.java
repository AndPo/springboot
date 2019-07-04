package lits.com.springboot.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class AuthRequest {

    @NotEmpty
    @Size(max = 64)
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$", message = "This is not email. Please verify" )
    private String email;

    @NotEmpty
    @Size(max = 32)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "Password must contains uppercase letter, lowercase letter and digit. Size must be with 8 letters or more")
    private String password;

}
