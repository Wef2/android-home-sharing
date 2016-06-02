package ac.jejunu.mcl.controller;

import ac.jejunu.mcl.entity.Rating;
import ac.jejunu.mcl.repository.HomeRepository;
import ac.jejunu.mcl.repository.RatingRepository;
import ac.jejunu.mcl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

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
    public Iterable<Rating> ratings() {
        return ratingRepository.findAll();
    }

    @RequestMapping("/rating/home/{home_id}/user/{user_id}")
    public Rating rating(@PathVariable("home_id") int home_id, @PathVariable("user_id") int user_id) {
        Rating rating = ratingRepository.findByHomeAndUser(home_id, user_id);
        System.out.println(rating);
        return rating;
    }

    @RequestMapping("/rating/home/{home_id}/average")
    public float rating(@PathVariable("home_id") int home_id) {
        return ratingRepository.averageHomeRating(home_id);
    }

    @RequestMapping(path = "/rating/add", method = RequestMethod.POST)
    public Rating addRating(@RequestParam(value = "user_id") int user_id,
                            @RequestParam(value = "home_id") int home_id,
                            @RequestParam(value = "score") float score) {

        try {
            Rating rating = ratingRepository.findByHomeAndUser(home_id, user_id);
            ratingRepository.updateScore(home_id, user_id, score);
            return ratingRepository.findByHomeAndUser(home_id, user_id);
        } catch (NoResultException e) {
            Rating newRating = new Rating();
            newRating.setUser(userRepository.findOne(user_id));
            newRating.setHome(homeRepository.findOne(home_id));
            newRating.setScore(score);
            return ratingRepository.save(newRating);
        }
    }

}