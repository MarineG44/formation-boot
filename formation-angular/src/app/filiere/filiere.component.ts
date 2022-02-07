import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Filiere, Formateur, Salle, Utilisateur } from '../model';
import { FiliereHttpService } from './filiere-http.service';

@Component({
  selector: 'app-filiere',
  templateUrl: './filiere.component.html',
  styleUrls: ['./filiere.component.css']
})
export class FiliereComponent implements OnInit {
  filiereForm: Filiere = null;

  constructor(private filiereService: FiliereHttpService, private route : ActivatedRoute) {
    this.route.params.subscribe(params => {
      this.filiereService.load();
      let id: number = params['id'];
      if(id) {
        this.edit(id);
      }
    });
   }

  ngOnInit(): void {
  }
  list(): Array<Filiere> {
    return this.filiereService.findAll();
  }

  add() {
    this.filiereForm = new Filiere();
    this.filiereForm.gestionnaire = new Formateur();
    this.filiereForm.referent = new Formateur();
    this.filiereForm.salle = new Salle();
  }

  edit(id: number) {
    this.filiereService.findById(id).subscribe(resp => {
      this.filiereForm = resp;

      if(!this.filiereForm.gestionnaire) {
        this.filiereForm.gestionnaire = new Formateur();
      }
      if(!this.filiereForm.referent) {
        this.filiereForm.referent = new Formateur();
      }
      if(!this.filiereForm.salle) {
        this.filiereForm.salle = new Salle();
      }
    }, err => console.log(err));
  }

  delete(id: number) {
   this.filiereService.deleteById(id);
  }

  save() {
    if(this.filiereForm.id) {
     this.filiereService.modify(this.filiereForm);
    } else {
      this.filiereService.create(this.filiereForm);
    }
    this.cancel();
  }

  cancel() {
    this.filiereForm = null;
  }
}

