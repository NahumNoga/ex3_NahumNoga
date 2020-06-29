package ex3;

import ex3.beans.UserSession;
import ex3.filters.LoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@EnableWebMvc
@Configuration
public class Config implements WebMvcConfigurer {

    @Resource(name = "us")
    UserSession userSession;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginFilter(userSession)).addPathPatterns("/", "/search/**", "/history/**");
       // registry.addInterceptor(new LoginFilter()).addPathPatterns("/search");
       // registry.addInterceptor(new LoginFilter()).addPathPatterns("/history");
       // registry.addInterceptor(new LoginFilter()).addPathPatterns("/delete");

    }
}
