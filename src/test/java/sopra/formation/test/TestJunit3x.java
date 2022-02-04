package sopra.formation.test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.Assert;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import junit.framework.TestCase;
import sopra.formation.config.ApplicationConfig;
import sopra.formation.dao.IFiliereDao;
import sopra.formation.model.Dispositif;
import sopra.formation.model.Filiere;

public class TestJunit3x extends TestCase {

	private AnnotationConfigApplicationContext context;

	@Override
	protected void setUp() throws Exception {
		this.context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	}

	@Override
	protected void tearDown() throws Exception {
		this.context.close();
	}

	public void testFiliere() {
		IFiliereDao filiereDao = this.context.getBean(IFiliereDao.class);

		int nbStartFiliere = filiereDao.findAll().size();

		Filiere filiere = new Filiere();
		filiere.setDateDebut(LocalDate.of(2021, Month.NOVEMBER, 29));
		filiere.setDuree(45);
		filiere.setDispositif(Dispositif.CPRO);

		filiere = filiereDao.save(filiere);

		Filiere filiereBis = new Filiere();
		filiereBis.setDateDebut(LocalDate.of(2021, Month.DECEMBER, 16));
		filiereBis.setDuree(57);
		filiereBis.setDispositif(Dispositif.POEI);

		filiereBis = filiereDao.save(filiereBis);

		filiere = filiereDao.findById(filiere.getId()).get();

		Assert.assertEquals(0, filiere.getVersion());
		Assert.assertEquals(LocalDate.of(2021, Month.NOVEMBER, 29), filiere.getDateDebut());
		Assert.assertEquals(45, filiere.getDuree());
		Assert.assertEquals(Dispositif.CPRO, filiere.getDispositif());

		filiere.setDateDebut(LocalDate.of(2021, Month.DECEMBER, 6));
		filiere.setDuree(57);
		filiere.setDispositif(Dispositif.POEI);

		filiere = filiereDao.save(filiere);

		filiere = filiereDao.findById(filiere.getId()).get();

		Assert.assertEquals(1, filiere.getVersion());
		Assert.assertEquals(LocalDate.of(2021, Month.DECEMBER, 6), filiere.getDateDebut());
		Assert.assertEquals(57, filiere.getDuree());
		Assert.assertEquals(Dispositif.POEI, filiere.getDispositif());

		int nbEndFiliere = filiereDao.findAll().size();

		Assert.assertEquals(2, nbEndFiliere - nbStartFiliere);

		filiereDao.deleteById(filiere.getId());

		Optional<Filiere> optFiliere = filiereDao.findById(filiere.getId());

		if (optFiliere.isPresent()) {
			Assert.fail("Erreur Suppression filière");
		}

		filiereDao.delete(filiereBis);

		optFiliere = filiereDao.findById(filiereBis.getId());

		Assert.assertTrue(optFiliere.isEmpty());
	}

	public void testMAtiere() {

	}
}
