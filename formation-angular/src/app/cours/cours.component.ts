import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Cours, Filiere, Formateur, Matiere } from '../model';
import { CoursHttpService } from './cours-http.service';

@Component({
  selector: 'app-cours',
  templateUrl: './cours.component.html',
  styleUrls: ['./cours.component.css']
})
export class CoursComponent implements OnInit {

  coursForm: Cours = null;

  constructor(private coursService: CoursHttpService, private route : ActivatedRoute) {
    this.route.params.subscribe(params => {
      this.coursService.load();
      let id: number = params['id'];
      if(id) {
        this.edit(id);
      }
    });
   }

  ngOnInit(): void {
  }
  list(): Array<Cours> {
    return this.coursService.findAll();
  }

  add() {
    this.coursForm = new Cours();
    this.coursForm.filiere = new Filiere();
    this.coursForm.formateur = new Formateur();
    this.coursForm.matiere = new Matiere();
  }

  edit(id: number) {
    this.coursService.findById(id).subscribe(resp => {
      this.coursForm = resp;

      if(!this.coursForm.filiere) {
        this.coursForm.filiere = new Filiere();
      }
      if(!this.coursForm.formateur) {
        this.coursForm.formateur = new Formateur();
      }
      if(!this.coursForm.matiere) {
        this.coursForm.matiere = new Matiere();
      }
    }, err => console.log(err));
  }

  delete(id: number) {
   this.coursService.deleteById(id);
  }

  save() {
    if(this.coursForm.id) {
     this.coursService.modify(this.coursForm);
    } else {
      this.coursService.create(this.coursForm);
    }
    this.cancel();
  }

  cancel() {
    this.coursForm = null;
  }
}

