import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from './../services/alert.service';
import { AuthService } from '../services/auth.service';


@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css'],

})
export class SigninComponent implements OnInit {

  userId: string;
  password: string;
  loading: string;


  constructor(private authsvc: AuthService, private alertService :AlertService, private router: Router, ) { }

  ngOnInit() { }

  signInUser() {
    this.authsvc.signin(this.userId, this.password).
      subscribe(response => {
        if (response["token"]) {
          this.authsvc.setToken(response["token"]);
          this.authsvc.setUserId(this.userId);
          //window.location.reload;
          //this.router.navigate(['/home']);
         window.location.href = 'http://localhost:4200/home';
        }
      }, error => {
        if (error.status === 401) {
          this.alertService.error('Username or password is incorrect.');
          console.log("Error");
        }
      });
  }
}

