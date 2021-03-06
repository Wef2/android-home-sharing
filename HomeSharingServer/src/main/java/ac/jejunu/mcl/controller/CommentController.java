package ac.jejunu.mcl.controller;

import ac.jejunu.mcl.entity.Comment;
import ac.jejunu.mcl.repository.CommentRepository;
import ac.jejunu.mcl.repository.HomeRepository;
import ac.jejunu.mcl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by neo-202 on 2016-06-01.
 */
@RestController
public class CommentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping("/comments")
    public Iterable<Comment> comments() {
        return commentRepository.findAll();
    }

    @RequestMapping(path = "/comment/add", method = RequestMethod.POST)
    public Comment addComment(@RequestParam(value = "user_id") int user_id,
                           @RequestParam(value = "home_id") int home_id,
                           @RequestParam(value = "content") String content) {
        Comment comment = new Comment();
        comment.setUser(userRepository.findOne(user_id));
        comment.setHome(homeRepository.findOne(home_id));
        comment.setContent(content);
        System.out.println(user_id);
        System.out.println(home_id);
        System.out.println(content);
        return commentRepository.save(comment);
    }
}
