package swjungle.springboard.dto;

import org.springframework.http.HttpHeaders;

public record LoginResponseDto(HttpHeaders headers, String message) {
    public LoginResponseDto(String message) {
        this(new HttpHeaders(), message);
    }
}
