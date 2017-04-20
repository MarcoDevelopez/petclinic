import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';

import { PetclinicService } from "app/services/petclinic.service";

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { OwnersComponent } from './components/owners/owners.component';
import { PetsComponent } from './components/pets/pets.component';
import { VetsComponent } from './components/vets/vets.component';
import { OwnerDetailsComponent } from './components/owner-details/owner-details.component';

const appRoutes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'owners', component: OwnersComponent },
  { path: 'owners/:id', component: OwnerDetailsComponent },
  { path: 'pets', component: PetsComponent },
  { path: 'vets', component: VetsComponent }
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DashboardComponent,
    OwnersComponent,
    PetsComponent,
    VetsComponent,
    OwnerDetailsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [PetclinicService],
  bootstrap: [AppComponent]
})
export class AppModule { }
