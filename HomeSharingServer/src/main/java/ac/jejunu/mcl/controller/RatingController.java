package ac.jejunu.mcl.controller;

import ac.jejunu.mcl.entity.Rating;
import ac.jejunu.mcl.repository.HomeRepository;
import ac.jejunu.mcl.repository.RatingRepository;
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
public class RatingController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @RequestMapping("/ratings")
    public Iterable<Rating> ratings(){
        return ratingRepository.findAll();
    }

    @RequestMapping(path = "/rating/add", method = RequestMethod.POST)
    public void addRating(@RequestParam int user_id, @RequestParam int home_id, @RequestParam float score){
        Rating rating = new Rating();
        rating.setUser(userRepository.findOne(user_id));
        rating.setHome(homeRepository.findOne(home_id));
        rating.setScore(score);
        ratingRepository.save(rating);
    }

}