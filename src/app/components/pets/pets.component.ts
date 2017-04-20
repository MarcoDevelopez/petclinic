import { Component, OnInit } from '@angular/core';
import { PetclinicService } from "app/services/petclinic.service";
import { Pet } from "../../pet";

@Component({
  selector: 'app-pets',
  templateUrl: './pets.component.html',
  styleUrls: ['./pets.component.css']
})
export class PetsComponent implements OnInit {

  pets: Pet[];

  constructor(private petclinicService: PetclinicService) { }

  ngOnInit() {
    this.petclinicService.getPets().subscribe(res => {
      this.pets = res;
    })
  }

}
