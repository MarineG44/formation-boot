import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConfigService } from '../app-config.service';
import { Filiere } from '../model';

@Injectable({
  providedIn: 'root'
})
export class FiliereHttpService {
  filieres: Array<Filiere> = new Array<Filiere>();
  stagiaireUrl : string;

  constructor(private http: HttpClient, private appConfig: AppConfigService) { 
    this.stagiaireUrl = this.appConfig.backEndUrl + "filiere/";
    this.load();
  }

  findAll(): Array<Filiere> {
    return this.filieres;
  }

  findById(id: number): Observable<Filiere> {
    return this.http.get<Filiere>(this.stagiaireUrl+id);
  }

  create(stagiaire: Filiere) {
    this.http.post<Filiere>(this.stagiaireUrl, stagiaire).subscribe(
      resp => {
        this.load();
      },
      error => console.log(error)
    );
  }

  modify(stagiaire: Filiere) {
    this.http.put<Filiere>(this.stagiaireUrl+stagiaire.id, stagiaire).subscribe(
      resp => {
        this.load();
      },
      error => console.log(error)
    );
  }

  deleteById(id: number) {
    this.http.delete<void>(this.stagiaireUrl+id).subscribe(
      resp => {
        this.load();
      },
      error => console.log(error)
    );
  }

  load() {
    this.http.get<Array<Filiere>>(this.stagiaireUrl)
    .subscribe(response => {
      this.filieres = response;     
    }, error => {
      console.log(error);
    });
  }
}
