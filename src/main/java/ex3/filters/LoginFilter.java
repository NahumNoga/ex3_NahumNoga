package ex3.filters;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("hello filter");
        HttpSession session = request.getSession();
        if(session.getAttribute("loggedIn") == null){
            session.setAttribute("loggedIn", false);
        }
        Boolean logged = (Boolean) session.getAttribute("loggedIn");
        if(!logged) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
