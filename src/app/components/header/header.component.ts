import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from "app/services/authentication.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private authService: AuthenticationService,
    private router: Router) {
   }

  ngOnInit() {
    
  }
 
  public get isLoggedIn() : boolean {
    return this.authService.isLoggedin;
  }
  
  logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }

}
