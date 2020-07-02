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

/**
 * class EX3Application
 * the main function of the application
 */
@SpringBootApplication
public class Ex3Application {

    //the queries
    @Autowired
    public UserRepository userRepository;

    /**
     * the bean of the user session
     * @return new instance of the session bean
     */
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserSession us() {
        return new UserSession();
    }

    /**
     * main function
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Ex3Application.class, args);
    }

}
