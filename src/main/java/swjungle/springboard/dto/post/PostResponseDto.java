package swjungle.springboard.dto.post;

import java.time.LocalDateTime;

public record PostResponseDto(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {}

