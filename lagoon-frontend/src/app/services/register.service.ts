import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Injectable} from "@angular/core";
import {User} from '../models/User';


@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  constructor (private http: HttpClient) {}

  sendUser(user:User) {
    let url = "http://localhost:8080/api/auth/register";
    let headers1 = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(url, JSON.stringify(user), {headers: headers1});
  }
}
