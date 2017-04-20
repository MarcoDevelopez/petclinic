import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import { Owner } from "../owner";
import { Pet } from "../pet";

@Injectable()
export class PetclinicService {

  private ownersUrl: string;
  private petsUrl: string;
  private vetsUrl: string;
  private urlAPI: string = "http://localhost:8082/petclinic/api";

  constructor(private http:Http) { }

  getOwners() {
    this.ownersUrl = "http://localhost:8082/petclinic/api/owners"; 
    return this.http.get(this.ownersUrl).map(res => res.json());
  }

  getOwner(id: number) {
    const ownerUrl = "http://localhost:8082/petclinic/api/owners/"+id;
    return this.http.get(ownerUrl).map(res => res.json());
  }

  saveOwner(owner: Owner) {
    const headers = new Headers({'Content-Type': 'application/json'});
    const url = "http://localhost:8082/petclinic/api/owners";
    return this.http.post(url, JSON.stringify(owner), {headers: headers}).map(res => res.json());
  }

  getPets() {
    this.petsUrl = "http://localhost:8082/petclinic/api/pets";
    return this.http.get(this.petsUrl).map(res => res.json());
  }

  savePet(ownerId: number, pet: Pet) {
    var url = this.urlAPI + "/owners/" + ownerId + "/pets";
    var headers = new Headers({'Content-Type': 'application/json'});
    return this.http.post(url, JSON.stringify(pet), {headers: headers}).map(res => res.json());
  }

  getPetTypes() {
    const url = "http://localhost:8082/petclinic/api/pets/types";
    return this.http.get(url).map(res => res.json());
  }

  getVets() {
    this.vetsUrl = "http://localhost:8082/petclinic/api/vets";
    return this.http.get(this.vetsUrl).map(res => res.json());
  }

}
