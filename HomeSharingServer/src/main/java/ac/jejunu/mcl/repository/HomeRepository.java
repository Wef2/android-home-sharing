package ac.jejunu.mcl.repository;

import ac.jejunu.mcl.entity.Home;
import ac.jejunu.mcl.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kim on 2016-04-23.
 */
public interface HomeRepository extends CrudRepository<Home, Integer> {
//
//    UPDATE table_name
//    SET column1=value1,column2=value2,...
//    WHERE some_column=some_value;

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Home SET file_id = :image_id WHERE id = :home_id", nativeQuery = true)
    void updateImage(@Param("home_id") int home_id, @Param("image_id") int image_id);

    List<Home> findByUser(User user);
}
