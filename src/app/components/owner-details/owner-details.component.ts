import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute, Params } from "@angular/router";
import { PetclinicService } from "app/services/petclinic.service";
import { Owner } from "../../owner";
import { Pet } from "../../pet";
import { Type } from "../../type";

@Component({
  selector: 'app-owner-details',
  templateUrl: './owner-details.component.html',
  styleUrls: ['./owner-details.component.css']
})
export class OwnerDetailsComponent implements OnInit {

  id: number;
  currentOwner: Owner = new Owner();

  petFormHeader: string;
  currentPet: Pet = new Pet();
  petTypes: Type[];

  constructor(
    private petclinicService: PetclinicService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.onLoadOwner();
    this.petclinicService.getPetTypes().subscribe(res => {
      this.petTypes = res;
    });
  }

  onLoadOwner() {
    this.id = this.route.snapshot.params['id'];
    this.petclinicService.getOwner(this.id).subscribe(res => {
      this.currentOwner = res;
    });
  }

  onSave() {
    this.petclinicService.saveOwner(this.currentOwner).subscribe(res => {
      this.currentOwner = res;
    });
  }

  onCancel() {
    this.onLoadOwner();
  }

  onAddPet() {
    this.petFormHeader = "Add a new Pet";
    this.currentPet = new Pet();
  }

  onEditPet(petId: number) {
    this.petFormHeader = "Edit Pet";
    for (var i = 0; i < this.currentOwner.pets.length; i++) {
      if(this.currentOwner.pets[i].id == petId) {
        this.currentPet = this.currentOwner.pets[i];
        break;
      }
    }
  }

  onSavePet() {
    for (var i = 0; i < this.petTypes.length; i++) {
      if (this.petTypes[i].id == this.currentPet.type.id) {
        this.currentPet.type.name = this.petTypes[i].name;
        break;
      }
    }
    
    this.petclinicService.savePet(this.id, this.currentPet).subscribe(res => {
      this.currentPet = res;
      var newPet = true;
      for (var i = 0; i < this.currentOwner.pets.length; i++) {
        if(this.currentOwner.pets[i].id == res.id) {
          this.currentOwner.pets[i] = res;
          newPet = false;
          break;
        }
      }
      if(newPet) {
        this.currentOwner.pets.push(res);
      }
    });
  }

}
