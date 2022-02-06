package sopra.formation.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("Stagiaire")
public class Stagiaire extends Utilisateur {
	@JsonView(Views.ViewStagiaire.class)
	private LocalDate dateNaissance;
	@OneToMany(mappedBy = "stagiaire")
	@JsonView(Views.ViewStagiaireDetail.class)
	private List<Cursus> cursus = new ArrayList<>();

	public Stagiaire() {
		super();
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public List<Cursus> getCursus() {
		return cursus;
	}

	public void setCursus(List<Cursus> cursus) {
		this.cursus = cursus;
	}

}
