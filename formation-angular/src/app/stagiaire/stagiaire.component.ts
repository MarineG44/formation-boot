import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Adresse, Stagiaire } from '../model';
import { StagiaireHttpService } from './stagiaire-http.service';

@Component({
  selector: 'app-stagiaire',
  templateUrl: './stagiaire.component.html',
  styleUrls: ['./stagiaire.component.css']
})
export class StagiaireComponent implements OnInit {

  stagiaireForm : Stagiaire;

  constructor(private stagiaireService: StagiaireHttpService, private route: ActivatedRoute) {
    this.route.params.subscribe(params => {
      this.stagiaireService.load();
      let id: number = params['id'];
      if(id) {
        this.edit(id);
      }
    });
   }

  ngOnInit(): void {
  }

  list(): Array<Stagiaire> {
    return this.stagiaireService.findAll();
  }

  add() {
    this.stagiaireForm = new Stagiaire();
    this.stagiaireForm.adr = new Adresse();
  }

  edit(id: number) {
    this.stagiaireService.findById(id).subscribe(resp => {
      this.stagiaireForm = resp;

      if(!this.stagiaireForm.adr) {
        this.stagiaireForm.adr = new Adresse();
      }
    }, err => console.log(err));
  }

  delete(id: number) {
    this.stagiaireService.deleteById(id);
   }

   save() {
    if(this.stagiaireForm.id) {
     this.stagiaireService.modify(this.stagiaireForm);
    } else {
      this.stagiaireService.create(this.stagiaireForm);
    }
    this.cancel();
  }

  cancel() {
    this.stagiaireForm = null;
  }

}
