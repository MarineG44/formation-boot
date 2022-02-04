package sopra.formation.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sopra.formation.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
//@ContextConfiguration(locations = "classpath:application-context.xml")
public class TestJunit4WithSpring {
	@Test
	public void bidon() {

	}

	@Test
	public void fournisseur() {

	}

}
