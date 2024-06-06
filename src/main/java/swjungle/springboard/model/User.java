package swjungle.springboard.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[a-z0-9]+$", message = "Username must be lowercase alphanumeric")
    @Column(unique = true, nullable = false)
    private String userName;

    @NotBlank
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password must be alphanumeric")
    @Column(nullable = false)
    private String password;

    // 기본 생성자
    public User() {
    }

    // 매개변수 있는 생성자
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
