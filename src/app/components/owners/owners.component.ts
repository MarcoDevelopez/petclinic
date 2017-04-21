import { Component, OnInit } from '@angular/core';
import { PetclinicService } from "app/services/petclinic.service";
import { Owner } from "../../owner";

@Component({
  selector: 'app-owners',
  templateUrl: './owners.component.html',
  styleUrls: ['./owners.component.css']
})
export class OwnersComponent implements OnInit {

  owners: Owner[];
  owner: Owner = new Owner();

  constructor(private petclinicService: PetclinicService) { }

  ngOnInit() {
    this.petclinicService.getOwners().subscribe(res => {
      this.owners = res;
    });
  }

  onAddOwner() {
    this.owner = new Owner();
  }

  onSaveOwner() {
    this.petclinicService.saveOwner(this.owner).subscribe(res => {
      this.owner = res;
      this.owners.push(res);
    });
  }

}
