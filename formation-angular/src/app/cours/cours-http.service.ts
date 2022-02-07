import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfigService } from '../app-config.service';
import { Cours } from '../model';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CoursHttpService {

  cours: Array<Cours> = new Array<Cours>();
  coursUrl: string;

  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.coursUrl = this.appConfig.backEndUrl + "cours/";
    this.load();
  }
  findAll(): Array<Cours> {
    return this.cours;
  }

  findById(id: number): Observable<Cours> {
    return this.http.get<Cours>(this.coursUrl+id);
  }

  create(cours: Cours) {
    this.http.post<Cours>(this.coursUrl, cours).subscribe(
      resp => {
        this.load();
      },
      error => console.log(error)
    );
  }

  modify(cours: Cours) {
    this.http.put<Cours>(this.coursUrl+cours.id, cours).subscribe(
      resp => {
        this.load();
      },
      error => console.log(error)
    );
  }

  deleteById(id: number) {
    this.http.delete<void>(this.coursUrl+id).subscribe(
      resp => {
        this.load();
      },
      error => console.log(error)
    );
  }

  load() {
    this.http.get<Array<Cours>>(this.coursUrl)
    .subscribe(response => {
      this.cours = response;     
    }, error => {
      console.log(error);
    });
  }

}
