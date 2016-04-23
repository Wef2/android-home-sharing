package ac.jejunu.mcl;

import ac.jejunu.mcl.entity.Home;
import ac.jejunu.mcl.repository.HomeRepository;
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

    @RequestMapping("/home/{id}")
    public Home home(@PathVariable Long id) {
        return homeRepository.findOne(id);
    }

}
