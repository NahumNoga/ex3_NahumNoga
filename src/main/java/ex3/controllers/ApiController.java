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

/**
 * class ApiController
 * rest controller for ajax requests
 */
@RestController
public class ApiController {

    // the queries
    @Autowired
    private UserRepository repository;

    // session data
    @Resource(name = "us")
    UserSession userSession;

    /**
     * get repository
     * @return repository of the queries
     */
    private UserRepository getRepo() {
        return repository;
    }

    /**
     * delete history
     * delete the data fro User table
     * @return json with mag if success to delete
     * @throws JSONException if problem with the Json
     */
    @PostMapping("/api/delete-history")
    public @ResponseBody String delete() throws JSONException {
        JSONObject json = new JSONObject();

        if(userSession.getLogged()) {  //if the user logged in
            getRepo().deleteAll();
            json.put("success", "1");
            json.put("msg", "history deleted");
        }else
            json.put("success", "0"); //not allow to delete
        return json.toString();
    }
}
