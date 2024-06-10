package swjungle.springboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    @NotBlank(message = "Username 은 필수입니다.")
    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[a-z0-9]+$", message = "Username 은 소문자 알파벳 또는 숫자로 구성되어야 합니다.")
    private String userName;

    @NotBlank(message = "Password 은 필수입니다.")
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$"
            , message = "Password 은 알파벳 대소문자, 숫자, 특수문자로 구성되어야 합니다.")
    private String password;
}
