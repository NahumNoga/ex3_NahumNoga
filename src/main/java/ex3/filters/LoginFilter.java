package ex3.filters;

import ex3.beans.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * class loginFilter
 * interceptor all request and check in the session if user logged
 */
public class LoginFilter implements HandlerInterceptor {

    // the session
    private UserSession userSession;

    /**
     * constructor
     * @param userSession- the session bean of the user
     */
    public LoginFilter(UserSession userSession) {
        this.userSession = userSession;
    }

    /**
     * handle before any request
     * @param request
     * @param response
     * @param handler
     * @return true if allow to continue with the reqest or false if not
     * @throws Exception if there any error
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       if(userSession.getLogged() == null){
           userSession.setLogged(false);
       }
       // if user is not login
       if(!userSession.getLogged()){
           response.sendRedirect("/login");
           return false;
       }
       return true;
    }
}
