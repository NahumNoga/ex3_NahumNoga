package ex3.controllers;

import ex3.beans.UserSession;
import ex3.repo.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ApiController {

    @Autowired
    private UserRepository repository;

    @Resource(name = "us")
    UserSession userSession;

    private UserRepository getRepo() {
        return repository;
    }

    @PostMapping("/api/delete-history")
    public @ResponseBody
    String delete() throws JSONException {
        JSONObject json = new JSONObject();

        if(userSession.getLogged()) {
            getRepo().deleteAll();
            json.put("success", "1");
            json.put("msg", "history deleted");
        }else
            json.put("success", "0");
        return json.toString();
    }
}
