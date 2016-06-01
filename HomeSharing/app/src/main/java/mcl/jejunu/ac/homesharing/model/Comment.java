package mcl.jejunu.ac.homesharing.model;

/**
 * Created by Kim on 2016-05-27.
 */
public class Comment {

    private int id;
    private User user;
    private Home home;
    private String content;

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
