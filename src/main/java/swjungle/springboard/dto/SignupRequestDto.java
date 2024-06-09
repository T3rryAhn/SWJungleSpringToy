package swjungle.springboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    @NotBlank(message = "Username is mandatory")
    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[a-z0-9]+$", message = "Username must be lowercase alphanumeric")
    private String userName;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password must be alphanumeric")
    private String password;
}
