package sopra.formation.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import sopra.formation.dao.ICursusDao;
import sopra.formation.model.Cursus;
import sopra.formation.model.Views;

@RestController
@RequestMapping("/api/cursus")
@CrossOrigin("*")
public class CursusRestController {
	
	@Autowired
	private ICursusDao cursusDao;
	
	@GetMapping("")
	@JsonView(Views.ViewCursus.class)
	public List<Cursus> findAll() {
		List<Cursus> cursus = cursusDao.findAll();

		return cursus;
	}
	
	@GetMapping("/{id}")
	@JsonView(Views.ViewCursus.class)
	public Cursus findById(@PathVariable Long id) {
		Optional<Cursus> optCursus = cursusDao.findById(id);

		if (optCursus.isPresent()) {
			return optCursus.get();
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cursus non trouvé");
	}
	
	@PostMapping("")
	@JsonView(Views.ViewCursus.class)
	public Cursus create(@RequestBody Cursus cursus) {
		cursus = cursusDao.save(cursus);

		return cursus;
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.ViewCursus.class)
	public Cursus update(@PathVariable Long id, @RequestBody Cursus cursus) {
		if (!cursusDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cursus non trouvé");
		}

		cursus = cursusDao.save(cursus);

		return cursus;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!cursusDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cursus non trouvé");
		}

		cursusDao.deleteById(id);
	}

}
