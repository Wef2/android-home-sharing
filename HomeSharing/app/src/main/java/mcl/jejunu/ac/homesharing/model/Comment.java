package mcl.jejunu.ac.homesharing.model;

/**
 * Created by Kim on 2016-05-27.
 */
public class Comment {

    private int id;
    private String writer;
    private String letter;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }
}
