package ac.jejunu.mcl.controller;

import ac.jejunu.mcl.entity.Reservation;
import ac.jejunu.mcl.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by neo-202 on 2016-06-01.
 */
@RestController
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @RequestMapping("/reservations")
    public Iterable<Reservation> reservations(){
        return reservationRepository.findAll();
    }
}
