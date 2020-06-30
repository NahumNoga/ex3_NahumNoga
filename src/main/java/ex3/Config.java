package ex3;

import ex3.beans.UserSession;
import ex3.filters.LoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * class config
 * configuration of web
 */
@EnableWebMvc
@Configuration
public class Config implements WebMvcConfigurer {

    // user session
    @Resource(name = "us")
    UserSession userSession;

    /**
     * add interceptor
     * the page we want to protect
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginFilter(userSession)).addPathPatterns("/", "/search/**", "/history/**");
    }
}
