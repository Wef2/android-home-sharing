package ac.jejunu.mcl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HomeSharingServerApplication.class)
@WebAppConfiguration
public class HomeSharingServerApplicationTests {

	@Test
	public void contextLoads() {
		File file = new File("c");
	}

}
