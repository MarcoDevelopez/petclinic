import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import { AppConfig } from "app/app.config";
import { Owner } from "../owner";
import { Pet } from "../pet";


@Injectable()
export class PetclinicService {

  constructor(
    private http:Http,
    private appConfig:AppConfig) { }

  createAuthorizationTokenHeader(headers: Headers) {
    var token = localStorage.getItem("jwtToken");
    if(token) {
      headers.append('Authorization', token);
    }
  }

  getOwners() {
    let url = this.appConfig.contextPath + "/api/owners"; 
    let headers = new Headers();
    this.createAuthorizationTokenHeader(headers);
    return this.http.get(url, {headers: headers}).map(res => res.json());
  }

  getOwner(id: number) {
    let url = this.appConfig.contextPath + "/api/owners/"+id;
    let headers = new Headers();
    this.createAuthorizationTokenHeader(headers);
    return this.http.get(url, {headers: headers}).map(res => res.json());
  }

  saveOwner(owner: Owner) {
    let url = this.appConfig.contextPath + "/api/owners";
    let headers = new Headers({'Content-Type': 'application/json'});
    this.createAuthorizationTokenHeader(headers);
    return this.http.post(url, JSON.stringify(owner), {headers: headers}).map(res => res.json());
  }

  getPets() {
    let url = this.appConfig.contextPath + "/api/pets";
    let headers = new Headers();
    this.createAuthorizationTokenHeader(headers);
    return this.http.get(url, {headers: headers}).map(res => res.json());
  }

  savePet(ownerId: number, pet: Pet) {
    let url = this.appConfig.contextPath + "/api/owners/" + ownerId + "/pets";
    let headers = new Headers({'Content-Type': 'application/json'});
    this.createAuthorizationTokenHeader(headers);
    return this.http.post(url, JSON.stringify(pet), {headers: headers}).map(res => res.json());
  }

  getPetTypes() {
    let url = this.appConfig.contextPath + "/api/pets/types";
    let headers = new Headers();
    this.createAuthorizationTokenHeader(headers);
    return this.http.get(url, {headers: headers}).map(res => res.json());
  }

  getVets() {
    let url = this.appConfig.contextPath + "/api/vets";
    let headers = new Headers();
    this.createAuthorizationTokenHeader(headers);
    return this.http.get(url, {headers: headers}).map(res => res.json());
  }

}
