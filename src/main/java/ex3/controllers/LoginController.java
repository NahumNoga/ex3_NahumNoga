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

/**
 * class LoginController
 * controller of the login page
 */
@Controller
public class LoginController {

    // value from application.properties
    @Value("${user}")
    private String user;
    @Value("${password}")
    private String password;

    // the session bean
    @Resource(name="us")
    private UserSession userSession;

    /**
     * login
     * display the login page
     * @param model -model of spring
     * @return- template of html page
     */
    @GetMapping("/login")
    public String login(Model model){
       return "login";
    }

    /**
     * register
     * check the input of the user and if its ok transfer to main page of web
     * @param name- name of the user
     * @param pwd- password of the user
     * @param model- model of spring
     * @return - the search (main) page if allowed and login page if not
     */
    @PostMapping("/login/register")
    public String register(@RequestParam(name="userName") String name,
                            @RequestParam(name="password") String pwd, Model model){
        String errorMsg;
        if( !name.equals(user) || !pwd.equals(password)){
            errorMsg = "Wrong data";
            model.addAttribute("errorMsg", errorMsg);
            return "login";
        }
        // set session that logged
        userSession.setLogged(true);

        // to landing page
        return "redirect:/";
    }

    /**
     * logout
     * upadte the session and redirect to login page
     * @param model- model of spring
     * @return - login page
     */
    @PostMapping("login/logout")
    public String logout(Model model){
        userSession.setLogged(false);
        return "redirect:/login";
    }
}
