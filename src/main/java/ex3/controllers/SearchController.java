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

    private ApplicationContext context;

    @Autowired
    private UserRepository repository;

    private UserRepository getRepo() {
        return repository;
    }

    public SearchController(ApplicationContext ctx){
        this.context = ctx;
    }

    @GetMapping("/")
    public String landingPage(Model model){
       // model.addAttribute("login", null);
        //model.addAttribute("followers", null);
        return "searchUser";
    }

    @PostMapping("/search")
    public String search(@RequestParam(name="userName") String userName,
                                       @Valid User user, BindingResult result, Model model) throws IOException, JSONException {
        String url = "https://api.github.com/users/" + userName;
        JSONObject json = JsonReader.readJsonFromUrl(url);
        if(!result.hasErrors()){
            if(json.getString("success").equals("1")){
                //  user.setUserName(json.getString("login"));

                if(checkUser(userName)){
                    User currUser = getRepo().findByUserName(userName);
                    long numOfSerch = currUser.getNumOfSearches();
                    numOfSerch++;
                    currUser.setNumOfSearches(numOfSerch);
                    getRepo().save(currUser);
                   // return "searchUser";
                }
                else{
                    user.setNumOfSearches(1);
                    user.setLink(json.getString("html_url"));
                    getRepo().save(user);
                }

                //UserRepository repo = (UserRepository) this.context.getBean(UserRepository.class);

                model.addAttribute("login", json.getString("login"));
                String numOfFollowers = json.getString("followers");
                if(numOfFollowers.equals("0"))
                    model.addAttribute("followers", "no followers");
                else
                    model.addAttribute("followers", numOfFollowers);
            }else{
                model.addAttribute("errorMsg", json.getString("errorMsg"));
            }
        }

        return "searchUser";
    }

    public boolean checkUser(String userName){
       // UserRepository repo = (UserRepository) this.context.getBean(UserRepository.class);
        User user = getRepo().findByUserName(userName);
        if(user != null){
            if(user.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

}
