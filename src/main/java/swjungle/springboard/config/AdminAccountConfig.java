package swjungle.springboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import swjungle.springboard.model.Role;
import swjungle.springboard.model.User;
import swjungle.springboard.repository.UserRepository;

@Configuration
public class AdminAccountConfig {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminAccountConfig(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner createAdminAccount() {
        return args -> {
            String adminUsername = "admin";
            String adminPassword = "@Password1234";

            // Check if the admin user already exists
            if (userRepository.findByUserName(adminUsername).isEmpty()) {
                // Create the admin user
                User admin = new User(adminUsername, passwordEncoder.encode(adminPassword), Role.ADMIN);
                userRepository.save(admin);
                System.out.println("Admin user created.");
            } else {
                System.out.println("Admin user already exists.");
            }
        };
    }
}