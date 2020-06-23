package ex3;

import ex3.filters.LoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginFilter()).addPathPatterns("/");
        registry.addInterceptor(new LoginFilter()).addPathPatterns("/search");
        registry.addInterceptor(new LoginFilter()).addPathPatterns("/history");
        registry.addInterceptor(new LoginFilter()).addPathPatterns("/delete");

    }
}
