package swjungle.springboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public record PostResponseDto(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {}

