import { Injectable } from '@angular/core';
import { Http, Headers } from "@angular/http";
import { AppConfig } from "app/app.config";
import 'rxjs/add/operator/map';

@Injectable()
export class AuthenticationService {

  isLoggedin: boolean = false;

  constructor(
    private http:Http,
    private appConfig:AppConfig) { }

  createAuthorizationTokenHeader(headers: Headers) {
    var token = localStorage.getItem("jwtToken");
    if(token) {
      headers.append('Authorization', token);
    }
  }

  getUserAuth() {
    let url = this.appConfig.contextPath + "/api/user";
    let headers = new Headers();
    this.createAuthorizationTokenHeader(headers);
    return this.http.get(url, {headers: headers}).map(res => res.json());
  }

  login(username: string, password: string) {
    this.isLoggedin = false;

    let url = this.appConfig.contextPath + "/auth";
    let formData = {username: username, password: password};
    let headers = new Headers({'Content-Type': 'application/json; charset=utf-8'});

    return this.http.post(url, JSON.stringify(formData), {headers: headers}).map(res => {
      
      // login success
      let token = res.json().token;

      if(token) {
        localStorage.setItem("jwtToken", token);
        this.isLoggedin = true;
      }
      
    });
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem("jwtToken");
    this.isLoggedin = false;
  }

}
