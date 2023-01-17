package uz.pdp.appjwtrealemailauditing.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginDto {

    @NotNull
    @Email
    private String username;
    @NotNull
    private String password;

}
