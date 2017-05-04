import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';

import { AppConfig } from "app/app.config";
import { PetclinicService } from "app/services/petclinic.service";
import { AuthenticationService } from "app/services/authentication.service";
import { AuthGuardService } from "app/services/auth.guard.service";

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { OwnersComponent } from './components/owners/owners.component';
import { PetsComponent } from './components/pets/pets.component';
import { VetsComponent } from './components/vets/vets.component';
import { OwnerDetailsComponent } from './components/owner-details/owner-details.component';
import { LoginComponent } from './components/login/login.component';
import { LandingComponent } from './components/landing/landing.component';

const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', component: LandingComponent },
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuardService] },
  { path: 'owners', component: OwnersComponent, canActivate: [AuthGuardService] },
  { path: 'owners/:id', component: OwnerDetailsComponent, canActivate: [AuthGuardService] },
  { path: 'pets', component: PetsComponent, canActivate: [AuthGuardService] },
  { path: 'vets', component: VetsComponent, canActivate: [AuthGuardService] },

  // otherwise redirect to home
  { path: '**', redirectTo: '' }
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
    OwnerDetailsComponent,
    LoginComponent,
    LandingComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes, {useHash: true})
  ],
  providers: [PetclinicService, AuthenticationService, AuthGuardService, AppConfig],
  bootstrap: [AppComponent]
})
export class AppModule { }
