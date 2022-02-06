package sopra.formation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Matiere {
	@Id
	@GeneratedValue
	@JsonView(Views.ViewCommon.class)
	private Long id;
	@Version
	@JsonView(Views.ViewCommon.class)
	private int version;
	@Column(length = 255)
	@JsonView(Views.ViewCommon.class)
	private String titre;
	@JsonView(Views.ViewCommon.class)
	private int duree;
	@Column(length = 4000)
	@JsonView(Views.ViewCommon.class)
	private String objectifs;
	@Column(length = 4000)
	@JsonView(Views.ViewCommon.class)
	private String preRequis;
	@Column(length = 4000)
	@JsonView(Views.ViewCommon.class)
	private String programme;
	@OneToMany(mappedBy = "matiere")
	@JsonView(Views.ViewMatiereDetail.class)
	private List<Cours> cours = new ArrayList<>();
	@OneToMany(mappedBy = "matiere")
	@JsonView(Views.ViewMatiereDetail.class)
	private List<Competence> competences = new ArrayList<>();

	public Matiere() {
		super();
	}

	public Matiere(String titre, int duree) {
		super();
		this.titre = titre;
		this.duree = duree;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getObjectifs() {
		return objectifs;
	}

	public void setObjectifs(String objectifs) {
		this.objectifs = objectifs;
	}

	public String getPreRequis() {
		return preRequis;
	}

	public void setPreRequis(String preRequis) {
		this.preRequis = preRequis;
	}

	public String getProgramme() {
		return programme;
	}

	public void setProgramme(String programme) {
		this.programme = programme;
	}

	public List<Cours> getCours() {
		return cours;
	}

	public void setCours(List<Cours> cours) {
		this.cours = cours;
	}

	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

}
