package ex3.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

@Component
public class UserSession implements Serializable {

    private static final long serialVersionUID = -3554327313077716240L;

    private Boolean isLogged;

    public UserSession(){
        isLogged = false;
    }

    public void setLogged(Boolean logged) {
        isLogged = logged;
    }

    public Boolean getLogged() {
        return isLogged;
    }


}
