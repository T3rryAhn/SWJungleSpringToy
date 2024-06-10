package swjungle.springboard.dto.auth;

import org.springframework.http.HttpHeaders;

public record LoginResponseDto(HttpHeaders headers, String message) {
    public LoginResponseDto(String message) {
        this(new HttpHeaders(), message);
    }
}
