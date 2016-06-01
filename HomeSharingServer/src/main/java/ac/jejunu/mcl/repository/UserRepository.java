package ac.jejunu.mcl.repository;

import ac.jejunu.mcl.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by BK on 2016-06-01.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

}

