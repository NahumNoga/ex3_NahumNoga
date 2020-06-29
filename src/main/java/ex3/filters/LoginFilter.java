package ex3.filters;

import ex3.beans.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements HandlerInterceptor {


    private UserSession userSession;

    public LoginFilter(UserSession userSession) {
        this.userSession = userSession;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("hello filter");

       if(userSession.getLogged() == null){
           userSession.setLogged(false);
       }
       if(!userSession.getLogged()){
           response.sendRedirect("/login");
           return false;
       }
       return true;
    }
}
