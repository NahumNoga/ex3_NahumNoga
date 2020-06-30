package ex3.controllers;

import ex3.repo.User;
import ex3.repo.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * class HistoryController
 * controller of the history page
 */
@Controller
public class HistoryController {

    // the queries
    @Autowired
    private UserRepository repository;

    /**
     * get repository
     * @return repository of the queries
     */
    private UserRepository getRepo() {
        return repository;
    }

    /**
     * show history
     * display the html page of history. show the 10 popular searches from the table
     * @param model- model of spring
     * @return - template of html page
     */
    @GetMapping("/history")
    public String showHistory(Model model){
        List<User> users = getRepo().findFirst10ByOrderByNumOfSearchesDesc();
        model.addAttribute("users", users);
        return "history";
    }





}
