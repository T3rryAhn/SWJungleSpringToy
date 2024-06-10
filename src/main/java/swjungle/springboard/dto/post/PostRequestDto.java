package swjungle.springboard.dto.post;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class PostRequestDto {

    @NotBlank(message = "title is mandatory")
    private String title;

    @NotBlank
    private String content;
}
