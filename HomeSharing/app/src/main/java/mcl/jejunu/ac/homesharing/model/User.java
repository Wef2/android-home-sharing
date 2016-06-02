package mcl.jejunu.ac.homesharing.model;

/**
 * Created by neo-202 on 2016-05-31.
 */
public class User {

    private int id;
    private String nickname;
    private String description;

    public User(){

    }

    public User(int id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}



