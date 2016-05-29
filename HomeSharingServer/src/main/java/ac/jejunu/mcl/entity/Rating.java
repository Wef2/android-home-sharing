package ac.jejunu.mcl.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by BK on 2016-05-29.
 */
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long user_id;
    private Long home_id;
    private Long score;
}