package sopra.formation.rest;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import sopra.formation.dao.IMatiereDao;
import sopra.formation.model.Matiere;
import sopra.formation.model.Views;
import sopra.formation.rest.exception.MatiereValidationException;

@RestController
@RequestMapping("/api/matiere")
@CrossOrigin("*")
public class MatiereRestController {

	@Autowired
	private IMatiereDao matiereDao;

	@GetMapping("")
	@JsonView(Views.ViewMatiere.class)
	public List<Matiere> findAll() {
		List<Matiere> produits = matiereDao.findAll();

		return produits;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewMatiere.class)
	public Matiere findById(@PathVariable Long id) {
		Optional<Matiere> optMatiere = matiereDao.findById(id);

		if (optMatiere.isPresent()) {
			return optMatiere.get();
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Matière non trouvée");
	}

	@GetMapping("/{id}/detail")
	@JsonView(Views.ViewMatiereDetail.class)
	public Matiere detailById(@PathVariable Long id) {
		Optional<Matiere> optMatiere = matiereDao.findById(id);

		if (optMatiere.isPresent()) {
			return optMatiere.get();
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Matière non trouvée");
	}

	@PostMapping("")
	@JsonView(Views.ViewMatiere.class)
//	@PreAuthorize("hasRole('ADMIN')")
	public Matiere create(@RequestBody @Valid Matiere matiere, BindingResult result) {
		if (result.hasErrors()) {
			throw new MatiereValidationException();
		}

		matiere = matiereDao.save(matiere);

		return matiere;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewMatiere.class)
	public Matiere update(@PathVariable Long id, @RequestBody Matiere matiere) {
		if (!matiereDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Matière non trouvée");
		}

		matiere = matiereDao.save(matiere);

		return matiere;
	}

	@PatchMapping("/{id}")
	@JsonView(Views.ViewMatiere.class)
	public Matiere partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		Optional<Matiere> optMatiere = matiereDao.findById(id);

		if (optMatiere.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Matiere non trouvée");
		}

		Matiere produit = optMatiere.get();

		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Matiere.class, key);
			ReflectionUtils.makeAccessible(field);

			if (field.getType().equals(BigDecimal.class)) {
				value = new BigDecimal((Double) value);
			}

			ReflectionUtils.setField(field, produit, value);
		});

		matiereDao.save(produit);

		return produit;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!matiereDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Matière non trouvé");
		}

		matiereDao.deleteById(id);
	}

//	@GetMapping("/by-prixAchat/{prixAchat}")
//	@JsonView(Views.ViewProduit.class)
//	public List<Produit> findAllByPrixAchat(@PathVariable BigDecimal prixAchat) {
//		List<Produit> produits = produitDao.findAllByPrixAchat(prixAchat);
//
//		return produits;
//	}
}
