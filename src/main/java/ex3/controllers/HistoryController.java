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

@Controller
public class HistoryController {

    @Autowired
    private UserRepository repository;

    private UserRepository getRepo() {
        return repository;
    }

    @GetMapping("/history")
    public String showHistory(Model model){
        List<User> users = getRepo().findFirst10ByOrderByNumOfSearchesDesc();
        model.addAttribute("users", users);
        return "history";
    }





}
