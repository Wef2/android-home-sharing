package ac.jejunu.mcl.controller;

import ac.jejunu.mcl.entity.Rating;
import ac.jejunu.mcl.repository.HomeRepository;
import ac.jejunu.mcl.repository.RatingRepository;
import ac.jejunu.mcl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/rating/home/{home_id}/user/{user_id}")
    public Rating rating(@PathVariable("home_id") int home_id, @PathVariable("user_id") int user_id){
        Rating rating = ratingRepository.findByHomeAndUser(home_id, user_id);
        return rating;
    }

    @RequestMapping("/rating/home/{home_id}/average")
    public float rating(@PathVariable("home_id") int home_id){
        return ratingRepository.averageHomeRating(home_id);
    }

    @RequestMapping(path = "/rating/add", method = RequestMethod.POST)
    public void addRating(@RequestParam(value = "user_id") int user_id,
                          @RequestParam(value = "home_id") int home_id,
                          @RequestParam(value = "score") float score){
        Rating rating = new Rating();
        rating.setUser(userRepository.findOne(user_id));
        rating.setHome(homeRepository.findOne(home_id));
        rating.setScore(score);
        ratingRepository.save(rating);
    }

}