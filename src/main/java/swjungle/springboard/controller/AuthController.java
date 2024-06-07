package swjungle.springboard.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swjungle.springboard.dto.LoginRequestDto;
import swjungle.springboard.dto.LoginResponseDto;
import swjungle.springboard.dto.SignupRequestDto;
import swjungle.springboard.dto.SignupResponseDto;
import swjungle.springboard.service.AuthService;

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
        return ResponseEntity.ok(signupResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = authService.login(loginRequestDto);
        return ResponseEntity.ok(loginResponseDto);
    }

}
