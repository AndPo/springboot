package lits.com.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString(exclude = {"password"})
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotNull
    @NotEmpty
    @Size(max = 64)
    private String email;

    @NotNull
    @NotEmpty
    @Size(max = 32)
    private String password;

}
