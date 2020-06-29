package ex3.controllers;

import ex3.beans.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Value("${user}")
    private String user;
    @Value("${password}")
    private String password;

    @Resource(name="us")
    private UserSession userSession;

    @GetMapping("/login")
    public String login(Model model, HttpSession session){
      /*  System.out.println("hello login");
        if(session.getAttribute("loggedIn") == null){
            session.setAttribute("loggedIn", false);
        }
        Boolean logged = (Boolean)session.getAttribute("loggedIn");
        if(logged){
            return "searchUser";
        }
        else{
            return "login";
        }*/

       return "login";
    }

    @PostMapping("/login/register")
    public String checkLogin(@RequestParam(name="userName") String name,
                            @RequestParam(name="password") String pwd, HttpServletRequest request, Model model){
        String errorMsg;
        if( !name.equals(user) || !pwd.equals(password)){
            errorMsg = "Wrong data";
            model.addAttribute("errorMsg", errorMsg);
            return "login";
        }

        userSession.setLogged(true);
        //request.getSession().setAttribute("loggedIn", true);

        return "redirect:/";
    }

    @PostMapping("login/logout")
    public String logout(HttpServletRequest request){
        //request.getSession().setAttribute("loggedIn", false);
        userSession.setLogged(false);
        return "redirect:/login";
    }
}
