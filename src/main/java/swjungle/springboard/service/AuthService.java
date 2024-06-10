package swjungle.springboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swjungle.springboard.dto.LoginRequestDto;
import swjungle.springboard.dto.LoginResponseDto;
import swjungle.springboard.dto.SignupRequestDto;
import swjungle.springboard.dto.SignupResponseDto;
import swjungle.springboard.model.User;
import swjungle.springboard.repository.UserRepository;
import swjungle.springboard.util.JwtTokenUtil;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public SignupResponseDto register(SignupRequestDto signupRequestDto) {

        String userName = signupRequestDto.getUserName();
        String password = signupRequestDto.getPassword();

        Optional<User> found = userRepository.findByUserName(userName);

        if (found.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Password encoding
        String encodePassword = passwordEncoder.encode(password);

        // Dto to Entity conversion
        User user = new User(userName, encodePassword);
        userRepository.save(user);

        return new SignupResponseDto("User registered successfully");
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws AuthenticationException {
        String userName = loginRequestDto.getUserName();
        String password = loginRequestDto.getPassword();

        Optional<User> found = userRepository.findByUserName(userName);
        if (found.isPresent()) {
            User user = found.get();
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new AuthenticationServiceException("Wrong password");
            }
            String token = jwtTokenUtil.generateToken(userName);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
            return new LoginResponseDto(headers, "User logged in successfully");
        }
        return new LoginResponseDto("User not found");
    }
}
