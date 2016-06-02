package ac.jejunu.mcl.controller;

import ac.jejunu.mcl.entity.Reservation;
import ac.jejunu.mcl.repository.HomeRepository;
import ac.jejunu.mcl.repository.ReservationRepository;
import ac.jejunu.mcl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by neo-202 on 2016-06-01.
 */
@RestController
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HomeRepository homeRepository;

    @RequestMapping("/reservations")
    public Iterable<Reservation> reservations(){
        return reservationRepository.findAll();
    }

    @RequestMapping("/reservation/{id}")
    public Reservation reservation(@PathVariable("id") int id){
        return reservationRepository.findOne(id);
    }

    @RequestMapping(value = "/reservation/add", method = RequestMethod.POST)
    public Reservation addReservation(@RequestParam(value = "user_id") int user_id,
                                      @RequestParam(value = "home_id") int home_id,
                                      @RequestParam(value = "check_in") String check_in,
                                      @RequestParam(value = "check_out") String check_out,
                                      @RequestParam(value = "people") int people) {
        Reservation reservation = new Reservation();
        reservation.setUser(userRepository.findOne(user_id));
        reservation.setHome(homeRepository.findOne(home_id));
        reservation.setCheck_in(check_in);
        reservation.setCheck_out(check_out);
        reservation.setPeople(people);
        return reservationRepository.save(reservation);
    }
}
