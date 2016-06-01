package ac.jejunu.mcl.repository;

import ac.jejunu.mcl.entity.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by neo-202 on 2016-06-01.
 */
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    List<Reservation> findByHome_id(int home_id);
}