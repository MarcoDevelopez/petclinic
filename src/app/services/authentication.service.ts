import { Injectable } from '@angular/core';
import { Http, Headers } from "@angular/http";
import { AppConfig } from "app/app.config";
import 'rxjs/add/operator/map';

@Injectable()
export class AuthenticationService {

  constructor(
    private http:Http,
    private appConfig:AppConfig) { }

  login(username: string, password: string) {
    var url = this.appConfig.contextPath + "/auth";
    var formData = {username: username, password: password};
    var headers = new Headers({'Content-Type': 'application/json; charset=utf-8'});
    return this.http.post(url, JSON.stringify(formData), {headers: headers}).map(res => {
      
      // login success
      let token = res.json().token;

      console.log(token);

      if(token) {
        this.appConfig.setJwtToken(token);
      }
      
    });
  }

  logout() {
    // remove user from local storage to log user out
    this.appConfig.removeJwtToken();
  }

  isAuthenticated(): boolean {
    return this.appConfig.getJwtToken() != null;
  }

}
