import { Component, OnInit } from '@angular/core';
import { PetclinicService } from "../../services/petclinic.service";
import { AuthenticationService } from "app/services/authentication.service";
import { Owner } from "../../owner";
import { Pet } from "../../pet";
import { Vet } from "../../vet";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  currentUser: any = {};
  owners: Owner[];
  pets: Pet[];
  vets: Vet[];


  constructor(
    private petclinicService: PetclinicService,
    private authService: AuthenticationService) { }

  ngOnInit() {
    this.authService.getUserAuth().subscribe(res => {
      this.currentUser = res;
    });

    this.petclinicService.getOwners().subscribe(res => {
      this.owners = res;
    })

    this.petclinicService.getPets().subscribe(res => {
      this.pets = res;
    })

    this.petclinicService.getVets().subscribe(res => {
      this.vets = res;
    })
  }

}
