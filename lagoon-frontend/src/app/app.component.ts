import { Component } from '@angular/core';
import {UserService} from './services/user.service';
import {User} from './models/user';
import { ActivatedRoute, Params } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  user: User;

  constructor(  private userService: UserService, 
    private route: ActivatedRoute,
    private router: Router){

  }

  ngOnInit(){
    this.userService.getUserByName(localStorage.getItem("currentUserName")).subscribe(
      user => {
        this.user = user;
      }
      // ,
      // error => {
      //     this.router.navigate(['/login']);
      // }
    )
  }
}
