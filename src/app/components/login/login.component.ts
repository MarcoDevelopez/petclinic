import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { AuthenticationService } from "app/services/authentication.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;

  returnUrl: string;

  isAuthenticated: boolean;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.logout();
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'];
  }

  logout() {
    let val = this.authenticationService.isAuthenticated();
    if(val) {
      this.authenticationService.logout();
      this.router.navigateByUrl('/');
    } 
    
  }

  login() {
    this.authenticationService.login(this.username, this.password).subscribe(res => {
      if(this.returnUrl) {
        this.router.navigateByUrl(this.returnUrl);
      } else {
        this.router.navigateByUrl('/dashboard');
      }
    });
  }

}
