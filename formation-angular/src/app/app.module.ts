import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { StagiaireComponent } from './stagiaire/stagiaire.component';
import { CursusComponent } from './cursus/cursus.component';
import { AccueilComponent } from './accueil/accueil.component';
import { RouterModule, Routes } from '@angular/router';
import { StagiaireHttpService } from './stagiaire/stagiaire-http.service';
import { CoursComponent } from './cours/cours.component';
import { FormsModule } from '@angular/forms';


const routes: Routes = [
  {path:"accueil", component: AccueilComponent},
  {path:"stagiaire", component: StagiaireComponent},
  {path:"cursus", component: CursusComponent},
  {path:"cours", component: CoursComponent}

]
@NgModule({
  declarations: [
    AppComponent,
    StagiaireComponent,
    CursusComponent,
    AccueilComponent,
    CoursComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [StagiaireHttpService],
  bootstrap: [AppComponent]
})


export class AppModule { }
