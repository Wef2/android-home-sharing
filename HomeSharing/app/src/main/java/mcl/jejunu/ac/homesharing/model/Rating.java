package mcl.jejunu.ac.homesharing.model;

/**
 * Created by neo-202 on 2016-06-01.
 */
public class Rating {

    private int id;
    private User user;
    private Home home;
    private float score;

    public Rating() {
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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
