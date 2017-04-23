import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from "app/services/authentication.service";

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {

  constructor(private authService: AuthenticationService) { }

  ngOnInit() {
  }

  public get isLoggedIn() : boolean {
    return this.authService.isLoggedin;
  }

}
