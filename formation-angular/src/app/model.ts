
export abstract class Personne {
	id: number;
	version: number;
	civilite: string;
	nom: string;
	prenom: string;
	adresse : Adresse;
	
	constructor(id?: number, version?:number, civilite?: string, nom?: string, prenom?: string, adresse?: Adresse) {
		this.id = id;
		this.version = version;
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}
	
}
export class Cours {
	id: number;
	version: number;
	duree: number;
	ordre: number;
	filiere: Filiere;
	formateur: Formateur;
	matiere: Matiere;

	constructor(id?: number, version?:number, duree?: number, ordre?: number, filiere?: Filiere, formateur?: Formateur, matiere?: Matiere) {
		this.id = id;
		this.version = version;
		this.duree = duree;
		this.ordre = ordre;
		this.filiere = filiere;
		this.formateur = formateur;
	}
	
}
export class Matiere {
	id: number;
	version: number;
	titre: string;
	duree: number;
	objectifs: string;
	preRequis : string;
	programme: string;
	cours: Array<Cours>;
	
	constructor(id?: number, version?:number, titre?: string, duree?: number, objectifs?: string, preRequis?: string,programme?: string, cours?: Array<Cours>) {
		this.id = id;
		this.version = version;
		this.titre = titre,
		this.duree  = duree;
		this.objectifs = objectifs;
		this.preRequis = preRequis;
		this.programme = programme;
		this.cours = cours;
	}
	
}

export class Filiere
{
	id: number;
	version: number;
	duree: number;
	dateDebut: string;
	dispositif: string;
	gestionnaire: Utilisateur;
	referent: Formateur;
	salle: Slottable;
	cursus: Array<Cursus>;
	cours: Array<Cours>;

	constructor(id?: number, version?:number, titre?: string, duree?: number, objectifs?: string, dateDebut?: string, referent?: Formateur, salle?: Salle, gestionnaire?: Utilisateur, cursus?: Array<Cursus>, cours?: Array<Cours>) {
		this.id = id;
		this.version = version;
		this.dispositif = this.dispositif,
		this.duree  = duree;
		this.cursus= cursus;
		this.dateDebut = dateDebut;
		this.cours = cours;
		this.salle = salle;
		this.referent = referent;
		this.gestionnaire = gestionnaire;
}


export class Adresse {
	rue: string;
	complement: string;
	codePostal: string;
	ville: string;

	constructor(rue?: string, complement?: string, codePostal?: string, ville?: string) {
		this.rue = rue;
		this.complement = complement;
		this.codePostal = codePostal;
		this.ville = ville;
	}
}
