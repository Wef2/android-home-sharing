package ac.jejunu.mcl.repository;

import ac.jejunu.mcl.entity.Comment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by BK on 2016-05-29.
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {

}

