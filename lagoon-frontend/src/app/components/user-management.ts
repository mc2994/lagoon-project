import { Component, OnInit, OnDestroy } from '@angular/core';
import {User} from '../models/user';
import {UserService} from '../services/user.service';
import { PaginatedObject } from '../utils/paginated-object';
import { HttpParams } from '@angular/common/http';

@Component({
  selector: 'user-management',
  templateUrl: './user-management.component.html'
})
export class UserManagement implements OnInit {
  users = new Array<User>();
  registered: boolean = false;
  paginated = new PaginatedObject<User>();
  pageNumber = new Array<Number>();
  user = new User();

  page: number = 1;
  size: number = 10;

  previous: number = 1;

  constructor (private userService: UserService) {
    this.getAllUsers();
  }

  ngOnInit() {
    
  }

  getAllUsers(){
    this.userService.getAllUsers(this.page, this.size).subscribe(
      user =>{
          this.paginated = user;  
          let res = 100/10;
          this.pageNumber = new Array<Number>();
          for(let i=1; i<=res; i++){           
            this.pageNumber.push(i);
          }           
      }, error=>{
            console.log("error.."+error);
    });
  }

  nextPage(index:number){    
    if(this.previous > index){
      this.page = index;
      this.getAllUsers();
    }else if(this.previous < index){
      this.page=index;
      this.getAllUsers();
    }
    this.previous = index;
  }

  fetchUsersByName(){
      this.userService.fetchUsersByName(this.user.firstName).subscribe(
        users=>{
              this.paginated = users;
        }, error=>{

        });
  }
}