package ex3;

import ex3.beans.UserSession;
import ex3.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class Ex3Application {

    @Autowired
    public UserRepository userRepository;

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserSession us() {
        return new UserSession();
    }

    public static void main(String[] args) {
        SpringApplication.run(Ex3Application.class, args);
    }

}
