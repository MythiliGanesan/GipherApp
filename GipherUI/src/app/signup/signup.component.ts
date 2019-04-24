import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
//import { AlertService } from './../services/alert.service';
import { AuthService } from '../services/auth.service';
import { User } from '../user';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  lname: string;
  userId: string;
  fname: string;
  password: string;

  constructor(private authsvc:AuthService, private router: Router) { }

  public ngOnInit() {}
  
  user = new User();

  signUpUser() {
    this.authsvc.signUpUser(this.userId,this.password,this.fname,this.lname).then
      (obj=>{ if(obj){
          this.router.navigate(['/signin']);
        }else{
          console.log("no token found");
        }
      });
      
  }
}




  
  
  
  