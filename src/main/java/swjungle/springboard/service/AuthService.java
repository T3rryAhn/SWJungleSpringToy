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
import swjungle.springboard.model.Role;
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
            throw new IllegalArgumentException("중복된 username 입니다.");
        }

        // Password encoding
        String encodePassword = passwordEncoder.encode(password);

        // Dto to Entity conversion
        User user = new User(userName, encodePassword, Role.USER);
        userRepository.save(user);

        return new SignupResponseDto("회원가입에 성공했습니다.");
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws AuthenticationException {
        String userName = loginRequestDto.getUserName();
        String password = loginRequestDto.getPassword();

        Optional<User> found = userRepository.findByUserName(userName);
        if (found.isPresent()) {
            User user = found.get();
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new AuthenticationServiceException("회원을 찾을 수 없습니다.");
            }
            String token = jwtTokenUtil.generateToken(userName, user.getRole());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
            return new LoginResponseDto(headers, "로그인 성공.");
        }
        throw new AuthenticationServiceException("회원을 찾을 수 없습니다.");
    }
}
