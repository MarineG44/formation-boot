package sopra.formation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 20)
@DiscriminatorValue("Utilisateur")
public class Utilisateur {
	@Id
	@GeneratedValue
	@JsonView(Views.ViewCommon.class)
	private Long id;
	@Version
	@JsonView(Views.ViewCommon.class)
	private int version;
	@Column(length = 100)
	@JsonView(Views.ViewCommon.class)
	private String nom;
	@Column(length = 100)
	@JsonView(Views.ViewCommon.class)
	private String prenom;
	@Column(length = 255)
	@JsonView(Views.ViewCommon.class)
	private String email;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	@JsonView(Views.ViewCommon.class)
	private Droit droit;
	@Column(length = 15)
	@JsonView(Views.ViewCommon.class)
	private String telephone;
	@Column(length = 255)
	@JsonView(Views.ViewCommon.class)
	private String identifiant;
	@Column(length = 30)
	@JsonView(Views.ViewCommon.class)
	private String motDePasse;
	@Embedded
	@JsonView(Views.ViewCommon.class)
	private Adresse adr;
	@OneToMany(mappedBy = "gestionnaire")
	@JsonView(Views.ViewUtilisateurDetail.class)
	private List<Filiere> filieres = new ArrayList<>();

	public Utilisateur() {
		super();
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Droit getDroit() {
		return droit;
	}

	public void setDroit(Droit droit) {
		this.droit = droit;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public Adresse getAdr() {
		return adr;
	}

	public void setAdr(Adresse adr) {
		this.adr = adr;
	}

	public List<Filiere> getFilieres() {
		return filieres;
	}

	public void setFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}

}
