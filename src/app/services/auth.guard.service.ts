import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AppConfig } from "app/app.config";
 
@Injectable()
export class AuthGuardService implements CanActivate {

  private TOKEN_KEY: string = "jwtToken";
 
  constructor(
    private router: Router,
    private appConfig: AppConfig) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.appConfig.getJwtToken()) {
      // logged in so return true
      return true;
    }

    // not logged in so redirect to login page with the return url
    this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
    return false;
  }

}