import { Component } from '@angular/core';
import { User } from '../models/user';
import { RegisterService } from '../services/register.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'register',
  templateUrl: './register.component.html'
})
export class Register {
  newUser: User = new User();
  registered: boolean = false;
  userError: User = new User();

  constructor(private registerService: RegisterService) { }

  onSubmit() {
    console.log("submit test");
    this.registerService.sendUser(this.newUser)
      .subscribe(
        data => {
          this.registered = true;
          this.newUser = new User();
        },
        (error: HttpErrorResponse) => {
          this.userError = error.error;
        }

      );
  }
}