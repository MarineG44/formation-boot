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
        motDePasse?: string, adr?: Adresse) {
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
	}
	
}

export class Stagiaire extends Utilisateur {
    dateNaissance: string;
    cursus: Array<Cursus> = new Array<Cursus>();

    constructor(id?: number, version?:number, nom?: string, prenom?: string, email?: string, droit?: Droit, telephone?: string, identifiant?: string,
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
