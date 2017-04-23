import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from "app/services/authentication.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isAuthenticated: boolean;

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router) {
   }

  ngOnInit() {
    let val = this.authenticationService.isAuthenticated();
    if(val) {
      this.isAuthenticated = val;
    } else {
      this.logout();
    }
  }

  logout(): void {
    this.authenticationService.logout();
    this.isAuthenticated = false;
    this.router.navigateByUrl('/');
  }

}
