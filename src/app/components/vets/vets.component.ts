import { Component, OnInit } from '@angular/core';
import { PetclinicService } from "app/services/petclinic.service";
import { Vet } from "../../vet";

@Component({
  selector: 'app-vets',
  templateUrl: './vets.component.html',
  styleUrls: ['./vets.component.css']
})
export class VetsComponent implements OnInit {

  vets: Vet[];

  constructor(private petclinicService: PetclinicService) { }

  ngOnInit() {
    this.petclinicService.getVets().subscribe(res => {
      this.vets = res;
    })
  }

}
