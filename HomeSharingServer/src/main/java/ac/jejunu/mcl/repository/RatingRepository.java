package ac.jejunu.mcl.repository;

import ac.jejunu.mcl.entity.Rating;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by BK on 2016-05-29.
 */
public interface RatingRepository extends CrudRepository<Rating, Integer> {


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Rating SET score = :score WHERE home_id = :home_id AND user_id = :user_id", nativeQuery = true)
    void updateScore(@Param("home_id") int home_id, @Param("user_id")  int user_id, @Param("score") float score);

    @Query(value = "SELECT * FROM Rating WHERE home_id = :home_id AND user_id = :user_id", nativeQuery = true)
    Rating findByHomeAndUser(@Param("home_id") int home_id, @Param("user_id") int user_id);

    @Query(value = "SELECT AVG(score) FROM Rating r WHERE home_id = :home_id", nativeQuery = true)
    float averageHomeRating(@Param("home_id") int home_id);

    List<Rating> findByHome_id(int home_id);
}

