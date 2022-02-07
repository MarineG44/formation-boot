import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { StagiaireComponent } from './stagiaire/stagiaire.component';
import { CursusComponent } from './cursus/cursus.component';
import { AccueilComponent } from './accueil/accueil.component';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
  {path:"accueil", component: AccueilComponent},
  {path:"stagiaire", component: StagiaireComponent},
  {path:"cursus", component: CursusComponent}
]
@NgModule({
  declarations: [
    AppComponent,
    StagiaireComponent,
    CursusComponent,
    AccueilComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})


export class AppModule { }
