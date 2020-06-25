package ex3.repo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


/**
 * class User
 * this class hold data that we want to save about user from github
 * the data saved on database
 */
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

    /**
     * default constructor
     */
    public User(){}

    /**
     * constructor
     * @param name- name of user
     * @param urlPath - path to the user repository in github
     */
    public User(String name, String urlPath){
        this.userName = name;
        this.link = urlPath;
        //TODO: numOFsearches?
    }

    /**
     * get id
     * @return the id of the user in the DB
     */
    public long getId() { return id; }

    /**
     * set id
     * @param id
     */
    public void setId(long id) { this.id = id; }

    /**
     * set user name
     * @param userName - name of the user
     */
    public void setUserName(String userName) { this.userName = userName; }

    /**
     * get user name
     * @return user name
     */
    public String getUserName(){ return userName; }

    /**
     * set link to the user repository in github
     * @param link- urlPath
     */
    public void setLink(String link) { this.link = link; }

    /**
     * get link
     * @return urlPath
     */
    public String getLink() { return link; }

    /**
     * set num os searches
     * @param numOfSearches - the num of searches that done about him
     */
    public void setNumOfSearches(long numOfSearches) { this.numOfSearches = numOfSearches; }

   /* public void setNumOfFollowers(long numOfFollowers) {
        this.numOfFollowers = numOfFollowers;
    }

    public long getNumOfFollowers() {
        return numOfFollowers;
    }*/

    /**
     * get num of searches
     * @return num of searches
     */
    public long getNumOfSearches() { return numOfSearches; }

    /**
     * convert to string
     * print the data about the user
     * @return string of the data
     */
    @Override
    public String toString() {
        return "User{" + " id= " + id + ", userName=" + userName + ", link=" + link + ", numOfSearches=" + numOfSearches + "}";
    }
}
