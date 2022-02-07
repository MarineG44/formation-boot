import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Stagiaire } from '../model';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class StagiaireHttpService {

  stagiaires: Array<Stagiaire> = new Array<Stagiaire>();
  stagiaireUrl : string;

  constructor(private http: HttpClient, private appConfig: AppConfigService) { 
    this.stagiaireUrl = this.appConfig.backEndUrl + "stagiaire/";
    this.load();
  }

  findAll(): Array<Stagiaire> {
    return this.stagiaires;
  }

  findById(id: number): Observable<Stagiaire> {
    return this.http.get<Stagiaire>(this.stagiaireUrl+id);
  }

  create(stagiaire: Stagiaire) {
    this.http.post<Stagiaire>(this.stagiaireUrl, stagiaire).subscribe(
      resp => {
        this.load();
      },
      error => console.log(error)
    );
  }

  modify(stagiaire: Stagiaire) {
    this.http.put<Stagiaire>(this.stagiaireUrl+stagiaire.id, stagiaire).subscribe(
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
    this.http.get<Array<Stagiaire>>(this.stagiaireUrl)
    .subscribe(response => {
      this.stagiaires = response;     
    }, error => {
      console.log(error);
    });
  }
}
