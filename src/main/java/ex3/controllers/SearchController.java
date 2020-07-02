package ex3.controllers;

import ex3.repo.User;
import ex3.repo.UserRepository;
import ex3.utils.JsonReader;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class SearchController {

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
     * landing page
     * the main page of web
     * @param model- model of spring
     * @return - template of html page
     */
    @GetMapping("/")
    public String landingPage(Model model){
        return "searchUser";
    }

    /**
     * serach github's user from api of github, display the name and number of followers
     * and save in the DB the number of searches and link for github repos
     * @param userName- name of the github's user
     * @param user- object of User that insert to the DB. bean
     * @param result- binding result
     * @param model- model of spring
     * @return template of search html page with the injections of the model
     * @throws IOException - if there I|O exceptions
     * @throws JSONException - if there problem with the Json
     */
    @PostMapping("/search")
    public String search(@RequestParam(name="userName") String userName,
                         @Valid User user, BindingResult result, Model model) throws IOException, JSONException {
        // get the json from api of github
        String url = "https://api.github.com/users/" + userName;
        JSONObject json = JsonReader.readJsonFromUrl(url);
        //save in the table
        if(!result.hasErrors()){
            if(json.getString("success").equals("1")){
                // save the name with lower case
                user.setUserName(user.getUserName().toLowerCase());
                userName = userName.toLowerCase();
                // critical section when update the table
                synchronized (getRepo()) {
                    if (checkUser(userName)) {  //user exists in the table already
                        User currUser = getRepo().findByUserName(userName);
                        currUser.setNumOfSearches(currUser.getNumOfSearches() + 1);
                    } else { // new row
                        user.setNumOfSearches(1);
                        user.setLink(json.getString("html_url"));
                        getRepo().save(user);
                    }
                }

                // add attribute to the model
                model.addAttribute("login", json.getString("login"));
                String numOfFollowers = json.getString("followers");
                if(numOfFollowers.equals("0"))
                    model.addAttribute("followers", "no followers");
                else
                    model.addAttribute("followers", numOfFollowers);
            }else{ // no found the user in github api
                model.addAttribute("errorMsg", json.getString("errorMsg"));
            }
        }

        return "searchUser";
    }

    /**
     * check if the user already exists  in the table
     * @param userName- user name
     * @return true or false
     */
    public boolean checkUser(String userName){
        User user = getRepo().findByUserName(userName);
        if(user != null){
            if(user.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

}
