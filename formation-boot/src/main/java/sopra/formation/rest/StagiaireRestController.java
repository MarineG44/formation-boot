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

import sopra.formation.dao.IUtilisateurDao;
import sopra.formation.model.Stagiaire;
import sopra.formation.model.Utilisateur;
import sopra.formation.model.Views;

@RestController
@RequestMapping("/api/stagiaire")
@CrossOrigin("*")
public class StagiaireRestController {
	
	@Autowired
	private IUtilisateurDao utilisateurDao;
	
	@GetMapping("")
	@JsonView(Views.ViewStagiaire.class)
	public List<Utilisateur> findAll() {
		List<Utilisateur> stagiaires = utilisateurDao.findAll();

		return stagiaires;
	}
	
	@GetMapping("/{id}")
	@JsonView(Views.ViewStagiaire.class)
	public Stagiaire findById(@PathVariable Long id) {
		Optional<Stagiaire> optStagiaire = utilisateurDao.findStagiaireById(id);

		if (optStagiaire.isPresent()) {
			return optStagiaire.get();
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stagiaire non trouvé");
	}
	
	@PostMapping("")
	@JsonView(Views.ViewStagiaire.class)
	public Stagiaire create(@RequestBody Stagiaire stagiaire) {
		stagiaire = utilisateurDao.save(stagiaire);

		return stagiaire;
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.ViewStagiaire.class)
	public Stagiaire update(@PathVariable Long id, @RequestBody Stagiaire stagiaire) {
		if (!utilisateurDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stagiaire non trouvé");
		}

		stagiaire = utilisateurDao.save(stagiaire);

		return stagiaire;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!utilisateurDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stagiaire non trouvé");
		}

		utilisateurDao.deleteById(id);
	}

}
