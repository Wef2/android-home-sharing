package ac.jejunu.mcl.repository;

import ac.jejunu.mcl.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by BK on 2016-05-29.
 */
public interface CommentRepository extends CrudRepository<Comment, Integer> {

    List<Comment> findTop3ByHome_id(int home_id);
    List<Comment> findByHome_id(int home_id);
}

