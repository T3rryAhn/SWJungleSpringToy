package swjungle.springboard.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @NotBlank(message = "Username 을 입력하세요.")
    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[a-z0-9]+$", message = "잘못된 Username 형식입니다.")
    private String userName;

    @NotBlank(message = "Password 를 입력하세요.")
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$"
            , message = "잘못된 Password 형식입니다.")
    private String password;
}
