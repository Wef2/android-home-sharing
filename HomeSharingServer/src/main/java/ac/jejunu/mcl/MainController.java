package ac.jejunu.mcl;

import ac.jejunu.mcl.entity.Comment;
import ac.jejunu.mcl.entity.Home;
import ac.jejunu.mcl.entity.Rating;
import ac.jejunu.mcl.entity.User;
import ac.jejunu.mcl.repository.CommentRepository;
import ac.jejunu.mcl.repository.HomeRepository;
import ac.jejunu.mcl.repository.RatingRepository;
import ac.jejunu.mcl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kim on 2016-04-23.
 */
@RestController
public class MainController {

    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/users")
    public Iterable<User> userList() {
        return userRepository.findAll();
    }

    @RequestMapping("/home/{id}")
    public Home home(@PathVariable Integer id) {
        return homeRepository.findOne(id);
    }

    @RequestMapping("/home/list")
    public Iterable<Home> homeList(){
        return homeRepository.findAll();
    }

    @RequestMapping("/comment/list")
    public Iterable<Comment> commentList(){
        return commentRepository.findAll();
    }

    @RequestMapping("/rating/list")
    public Iterable<Rating> ratingList(){
        return ratingRepository.findAll();
    }

}