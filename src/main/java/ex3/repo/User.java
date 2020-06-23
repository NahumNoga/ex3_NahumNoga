package ex3.repo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "userName is mandatory")
    private String userName;
    @NotBlank(message = "link is mandatory")
    private String link;
    private long numOfSearches;
   // private long numOfFollowers;

    public User(){}

    public User(String name, String urlPath){
        this.userName = name;
        this.link = urlPath;
        //TODO: numOFsearches?
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUserName(){ return userName; }

    public void setLink(String link) { this.link = link; }

    public String getLink() { return link; }

    public void setNumOfSearches(long numOfSearches) { this.numOfSearches = numOfSearches; }

   /* public void setNumOfFollowers(long numOfFollowers) {
        this.numOfFollowers = numOfFollowers;
    }

    public long getNumOfFollowers() {
        return numOfFollowers;
    }*/

    public long getNumOfSearches() { return numOfSearches; }
    @Override
    public String toString() {
        return "User{" + " id= " + id + ", userName=" + userName + ", link=" + link + ", numOfSearches=" + numOfSearches + "}";
    }
}
