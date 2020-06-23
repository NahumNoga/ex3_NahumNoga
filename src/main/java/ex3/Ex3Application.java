package ex3;

import ex3.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex3Application {

    @Autowired
    public UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Ex3Application.class, args);
    }

}
