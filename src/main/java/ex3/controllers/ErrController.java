package ex3.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * class ErrController
 * handle errors and exceptions that happen in the application
 */
@Controller
public class ErrController implements ErrorController {

    /**
     * handle error
     * display a friendly error page
     * @return template of html page
     */
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute("javax.servlet.error.status_code");
        String errMsg = "Something wrong happen..";
        if(status != null){
            int statusCode = Integer.parseInt(status.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                errMsg = "Page not found";
            }
        }
        model.addAttribute("errMsg", errMsg);
        return "error";
    }

    /**
     * handle error
     * @return path to error page
     */
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
