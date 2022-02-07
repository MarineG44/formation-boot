export abstract class Utilisateur {
	id: number;
	version: number;
	nom: string;
	prenom: string;
    email: string;
    droit: string;
    telephone: string;
    identifiant: string;
    motDePasse: string;
	adr: Adresse;
    filieres: Array<Filiere> = new Array<Filiere>();
	
	constructor(id?: number, version?:number, nom?: string, prenom?: string, email?: string, droit?: string, telephone?: string, identifiant?: string,
        motDePasse?: string, adr?: Adresse, filiere?: Array<Filiere>) {
		this.id = id;
		this.version = version;
		this.nom = nom;
        this.prenom = prenom;
		this.email = email;
        this.droit = droit;
        this.telephone = telephone;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
		this.adr = adr;
		this.filieres = this.filieres;
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

export class Stagiaire extends Utilisateur {
    dateNaissance: string;
    cursus: Array<Cursus> = new Array<Cursus>();

    constructor(id?: number, version?:number, nom?: string, prenom?: string, email?: string, droit?: string, telephone?: string, identifiant?: string,
        motDePasse?: string, adr?: Adresse) {
            super(id, version, nom, prenom, email, droit, telephone, identifiant,
                motDePasse, adr)
            this.dateNaissance;
        }

}

export class Cursus {
    id: number;
    version: number;
    stagiaire: Stagiaire;
    filiere: Filiere;
    noteC: number;
    commentairesC: string;
    noteT: number;
    commentairesT: string;

    contructor(id?: number, version?: number, stagiaire?: Stagiaire, filiere?: Filiere, noteC?: number, commentairesC?: string, noteT?: number, commentairesT?: string) {
    this.id = id;
    this.version = version;
    this.stagiaire = stagiaire;
    this.filiere = filiere;
    this.noteC = noteC;
    this.commentairesC = commentairesC;
    this.noteT = noteT;
    this.commentairesT = commentairesT;

    }

}

export class Formateur extends Utilisateur {
	competences: Array<Competence>;
	absences: Array<Absence>;
	override filieres: Array<Filiere>;
	formateur: Formateur;

	constructor(id?: number, version?:number, nom?: string, prenom?: string, email?: string, droit?: string, telephone?: string, identifiant?: string, motDePasse?: string,
		 adr?: Adresse, competences?: Array<Competence>, absences?: Array<Absence>, filieres?: Array<Filiere>, formateur?: Formateur) {
		super(id, version, nom, prenom, email, droit, telephone, identifiant,
			motDePasse, adr, filieres)
		this.competences = competences;
		this.absences = this.absences;
		this.formateur = formateur;
		}
}


export class Competence {
    id: number;
    version: number;
    niveau: string;
	formateur: Formateur;
	matiere: Matiere;

	contructor(id?: number, version?: number, niveau?: string, formateur?: Formateur, matiere?: Matiere){
    this.id = id;
    this.version = version;
	this.niveau = niveau;
	this.formateur = formateur;
	this.matiere = matiere;
    }

}


export class Absence {
    id: number;
    version: number;
    date: string;
	formateur: Formateur;
	
    contructor(id?: number, version?: number, date?: string, formateur?: Formateur) {
    this.id = id;
    this.version = version;
    this.date= date;
	this.formateur = formateur;
    }

}
