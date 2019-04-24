import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signout',
  templateUrl: './signout.component.html',
  styleUrls: ['./signout.component.css'],
  encapsulation: ViewEncapsulation.None
})

export class SignOutComponent implements OnInit {

  constructor(private router: Router) {

    localStorage.removeItem('jwt_token');
    window.location.href = 'http://localhost:4200/signin';
  }

  public ngOnInit() {}

}