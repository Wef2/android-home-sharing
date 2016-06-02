package ac.jejunu.mcl.repository;

import ac.jejunu.mcl.entity.Home;
import ac.jejunu.mcl.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Kim on 2016-04-23.
 */
public interface HomeRepository extends CrudRepository<Home, Integer> {
//
//    UPDATE table_name
//    SET column1=value1,column2=value2,...
//    WHERE some_column=some_value;

    @Query(value = "UPDATE Home SET file_id = :image_id WHERE home_id = :home_id", nativeQuery = true)
    void updateImage(int home_id, int image_id);

    List<Home> findByUser(User user);
}
