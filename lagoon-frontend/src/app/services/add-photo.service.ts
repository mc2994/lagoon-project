import {Injectable} from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class AddPhotoService {
  constructor (private http: HttpClient) {}

  sendPhoto(formData: FormData) {
    let url = "http://localhost:8080/api/auth/photo/add";
    let headers1 = new HttpHeaders({'Authorization':'Bearer '+localStorage.getItem("token")});
    console.table(url);
    return this.http.post(url, formData, {headers: headers1});
  }
}