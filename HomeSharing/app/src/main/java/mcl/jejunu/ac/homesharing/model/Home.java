package mcl.jejunu.ac.homesharing.model;

/**
 * Created by Kim on 2016-05-06.
 */
public class Home {

    private int id;
    private User user;
    private Filedata filedata;
    private String name;
    private String description;
    private int people;
    private int charge;
    private double latitude;
    private double longitude;

    public Home() {
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

    public Filedata getFiledata() {
        return filedata;
    }

    public void setFiledata(Filedata filedata) {
        this.filedata = filedata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
