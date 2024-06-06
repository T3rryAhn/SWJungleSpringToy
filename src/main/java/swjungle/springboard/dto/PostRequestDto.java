package swjungle.springboard.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class PostRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String author;

    @NotBlank
    private String password;
}
