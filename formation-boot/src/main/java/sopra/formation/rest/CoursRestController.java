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

import sopra.formation.dao.ICoursDao;
import sopra.formation.model.Cours;
import sopra.formation.model.Views;
import sopra.formation.rest.exception.CoursValidationException;

@RestController
@RequestMapping("/api/cours")
@CrossOrigin("*")
public class CoursRestController {

	@Autowired
	private ICoursDao coursDao;

	@GetMapping("")
	@JsonView(Views.ViewCours.class)
	public List<Cours> findAll() {
		List<Cours> cours = coursDao.findAll();

		return cours;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewCours.class)
	public Cours findById(@PathVariable Long id) {
		Optional<Cours> optCours = coursDao.findById(id);

		if (optCours.isPresent()) {
			return optCours.get();
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Matière non trouvée");
	}

	@GetMapping("/{id}/detail")
	@JsonView(Views.ViewCoursDetail.class)
	public Cours detailById(@PathVariable Long id) {
		Optional<Cours> optCours = coursDao.findById(id);

		if (optCours.isPresent()) {
			return optCours.get();
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Matière non trouvée");
	}

	@PostMapping("")
	@JsonView(Views.ViewCours.class)
//	@PreAuthorize("hasRole('ADMIN')")
	public Cours create(@RequestBody @Valid Cours matiere, BindingResult result) {
		if (result.hasErrors()) {
			throw new CoursValidationException();
		}

		matiere = coursDao.save(matiere);

		return matiere;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewCours.class)
	public Cours update(@PathVariable Long id, @RequestBody Cours matiere) {
		if (!coursDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Matière non trouvée");
		}

		matiere = coursDao.save(matiere);

		return matiere;
	}

	@PatchMapping("/{id}")
	@JsonView(Views.ViewCours.class)
	public Cours partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		Optional<Cours> optCours = coursDao.findById(id);

		if (optCours.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cours non trouvée");
		}

		Cours cours = optCours.get();

		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Cours.class, key);
			ReflectionUtils.makeAccessible(field);

			if (field.getType().equals(BigDecimal.class)) {
				value = new BigDecimal((Double) value);
			}

			ReflectionUtils.setField(field, cours, value);
		});

		coursDao.save(cours);

		return cours;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!coursDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Matière non trouvé");
		}

		coursDao.deleteById(id);
	}

//	@GetMapping("/by-prixAchat/{prixAchat}")
//	@JsonView(Views.ViewProduit.class)
//	public List<Produit> findAllByPrixAchat(@PathVariable BigDecimal prixAchat) {
//		List<Produit> cours = coursDao.findAllByPrixAchat(prixAchat);
//
//		return cours;
//	}
}
