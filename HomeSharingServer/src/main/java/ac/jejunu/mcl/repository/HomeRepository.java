package ac.jejunu.mcl.repository;

import ac.jejunu.mcl.entity.Home;
import ac.jejunu.mcl.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Kim on 2016-04-23.
 */
public interface HomeRepository extends CrudRepository<Home, Integer> {
    List<Home> findByUser(User user);
}
