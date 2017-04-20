import { Component, OnInit } from '@angular/core';
import { PetclinicService } from "../../services/petclinic.service";
import { Owner } from "../../owner";
import { Pet } from "../../pet";
import { Vet } from "../../vet";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  owners: Owner[];
  pets: Pet[];
  vets: Vet[];

  constructor(private petclinicService: PetclinicService) { }

  ngOnInit() {
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
