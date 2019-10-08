import { Component } from '@angular/core';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'login',
  templateUrl: './login.component.html'
})
export class Login {
  error: string;
  public model = { 'userName': '', 'password': '' };
  public currentUserName;

  constructor(public loginService: LoginService) {
    this.currentUserName = localStorage.getItem("currentUserName");
  }

  onSubmit() {
    this.loginService.sendCredential(this.model).subscribe(
      data => {
        localStorage.setItem("token", JSON.parse(JSON.stringify(data["accessToken"])));
        localStorage.setItem("currentUserName", this.model.userName);
      },
      error =>{
        alert("Login error: "+this.error);
      });
  }
}