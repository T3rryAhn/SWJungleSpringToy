package swjungle.springboard.dto;

import jakarta.validation.constraints.NotBlank;

public record DeleteRequestDto(@NotBlank String password) {
}
