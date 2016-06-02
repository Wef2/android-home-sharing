package ac.jejunu.mcl.controller;

import ac.jejunu.mcl.entity.Home;
import ac.jejunu.mcl.entity.User;
import ac.jejunu.mcl.repository.HomeRepository;
import ac.jejunu.mcl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by neo-202 on 2016-06-01.
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HomeRepository homeRepository;

    @RequestMapping("/users")
    public Iterable<User> users(){
        return userRepository.findAll();
    }

    @RequestMapping("/user/{id}/homes")
    public Iterable<Home> homes(@PathVariable int id) {
        User user = userRepository.findOne(id);
        return homeRepository.findByUser(user);
    }
}
