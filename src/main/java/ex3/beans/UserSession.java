package ex3.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

/**
 * class UserSession
 * hold data that saved in session for any user of our application web
 */
@Component
public class UserSession implements Serializable {

    // for Serializable
    private static final long serialVersionUID = -3554327313077716240L;

    // if user logged to application
    private Boolean isLogged;

    /**
     * constructor
     */
    public UserSession(){
        isLogged = false;
    }

    /**
     * set logged
     * set flag if user logged or not
     * @param logged - true or false
     */
    public void setLogged(Boolean logged) {
        this.isLogged = logged;
    }

    /**
     * get logged
     * get the boolean if logged or not
     * @return isLogged- boolean flag
     */
    public Boolean getLogged() {
        return isLogged;
    }


}
