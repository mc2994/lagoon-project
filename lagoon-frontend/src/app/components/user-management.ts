import { Component, OnInit, OnDestroy } from '@angular/core';
import { User } from '../models/user';
import { UserService } from '../services/user.service';
import { PaginatedObject } from '../utils/paginated-object';
import { switchMap, catchError } from 'rxjs/operators';
import { Subscription, timer, throwError } from 'rxjs';
import { UtilityClass } from '../utils/lagoon-utility';

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
  subscription: Subscription;


  page: number = 1;
  size: number = 10;

  previous: number = 1;

  constructor(private userService: UserService) {

  }

  ngOnInit() {
    this.getAllUsers();
  }

  getAllUsers() {
    this.userService.getAllUsers(this.page, this.size).subscribe(
      user => {
        this.paginated = user;
        let res = 100 / 10;
        this.pageNumber = new Array<Number>();
        for (let i = 1; i <= res; i++) {
          this.pageNumber.push(i);
        }
      }, error => {
        console.log("error.." + error);
      });
  }

  nextPage(index: number) {
    if (this.previous > index) {
      this.page = index;
      this.getAllUsers();
    } else if (this.previous < index) {
      this.page = index;
      this.getAllUsers();
    }
    this.previous = index;
  }

  fetchUsersByName() {
    this.userService.fetchUsersByName(this.user.firstName).subscribe(
      users => {
        this.paginated = users;
      }, error => {

      });
  }

  exportRecordWithTimer() {
    this.subscription = timer(0, 10000).pipe(
      switchMap(() => this.userService.downloadPDF())
    ).subscribe(result => {
      var blob = new Blob([result.body], { type: "application/pdf" });
      let filename = UtilityClass.getFilename(result);
      UtilityClass.downloadFile(filename, blob);
      this.subscription.unsubscribe();
    }), catchError(error => {
      this.subscription.unsubscribe();
      return throwError("fileDownloadError" + error)

    })
  }
}