package ac.jejunu.mcl.controller;

import ac.jejunu.mcl.entity.Comment;
import ac.jejunu.mcl.entity.Home;
import ac.jejunu.mcl.entity.Rating;
import ac.jejunu.mcl.entity.Reservation;
import ac.jejunu.mcl.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Kim on 2016-04-23.
 */
@RestController
public class HomeController {

    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @RequestMapping("/home/{id}")
    public Home home(@PathVariable int id) {
        return homeRepository.findOne(id);
    }

    @RequestMapping("/home/list")
    public Iterable<Home> homeList() {
        return homeRepository.findAll();
    }

    @RequestMapping(path = "/home/add", method = RequestMethod.POST)
    public void addHome(@RequestParam int id, @RequestParam String name, @RequestParam String description,
                        @RequestParam int people, @RequestParam int charge,
                        @RequestParam double latitude, @RequestParam double longitude){
        Home home = new Home();
        home.setUser(userRepository.findOne(id));
        home.setName(name);
        home.setDescription(description);
        home.setPeople(people);
        home.setCharge(charge);
        home.setLatitude(latitude);
        home.setLongitude(longitude);
        homeRepository.save(home);
    }

    @RequestMapping(path = "/home/{id}/ratings", method = RequestMethod.GET)
    public Iterable<Rating> getRatingsById(@PathVariable int id){
        return ratingRepository.findByHome_id(id);
    }

    @RequestMapping(path = "/home/{id}/comments", method = RequestMethod.GET)
    public Iterable<Comment> getCommentsById(@PathVariable int id){
        return commentRepository.findByHome_id(id);
    }

    @RequestMapping(path = "/home/{id}/reservations", method = RequestMethod.GET)
    public Iterable<Reservation> getReservationsById(@PathVariable int id){
        return reservationRepository.findByHome_id(id);
    }
}