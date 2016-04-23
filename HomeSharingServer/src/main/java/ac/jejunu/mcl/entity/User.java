package ac.jejunu.mcl.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Kim on 2016-04-23.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long generated_id;
    private String user_id;
    private String password;
    private String nickname;
    private String type;
}
