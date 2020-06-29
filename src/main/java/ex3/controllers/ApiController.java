package ex3.controllers;

import ex3.repo.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private UserRepository repository;

    private UserRepository getRepo() {
        return repository;
    }

    @PostMapping("/api/delete-history")
    public @ResponseBody
    String delete() throws JSONException {
        getRepo().deleteAll();
        JSONObject json = new JSONObject("{\"msg\": \"history deleted\"}");
        return json.toString();
    }
}
