package swjungle.springboard.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swjungle.springboard.dto.auth.LoginRequestDto;
import swjungle.springboard.dto.auth.LoginResponseDto;
import swjungle.springboard.dto.auth.SignupRequestDto;
import swjungle.springboard.dto.auth.SignupResponseDto;
import swjungle.springboard.service.AuthService;

import javax.security.sasl.AuthenticationException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> register(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        SignupResponseDto signupResponseDto = authService.register(signupRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(signupResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDto loginRequestDto) throws AuthenticationException {
        LoginResponseDto loginResponseDto = authService.login(loginRequestDto);
        return ResponseEntity.status(HttpStatus.OK).headers(loginResponseDto.headers()).body(loginResponseDto.message());
    }

}
