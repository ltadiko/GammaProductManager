package nl.intergamma;

import nl.intergamma.entities.Role;
import nl.intergamma.entities.User;
import nl.intergamma.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class GammaProductManagerApplication {


    public static void main(String[] args) {
        SpringApplication.run(GammaProductManagerApplication.class, args);
    }

    @Bean
    public CommandLineRunner setupDefaultUser(UserService service) {
        return args -> {
            service.save(new User(
                    "admin", //username
                    "admin", //password
                    Arrays.asList(new Role("Admin"), new Role("ACTUATOR")),//roles
                    true//Active
            ));
            service.save(new User(
                    "user", //username
                    "user", //password
                    Arrays.asList(new Role("USER"), new Role("ACTUATOR")),//roles
                    true//Active
            ));
        };
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
