import { Observable } from 'rxjs/internal/Rx';
import {Injectable} from '@angular/core';
import {Photo} from '../models/photo';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {User} from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class PhotoService {

  constructor (private http:HttpClient){}

  getPhotos(){
    let url = "http://localhost:8080/api/auth/allPhotos";
    let headers1 = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem("token")});
    return this.http.get<Photo[]>(url, {headers: headers1});
  }

  getPhotoById (photoId: number) {
    let tokenUrl1 = "http://localhost:8080/api/auth/photo/photoId";
    let headers1 = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem("token")});
    return this.http.post<Photo>(tokenUrl1, JSON.stringify(photoId), {headers: headers1});
  }

  getPhotosByUser (user: User) {
    let tokenUrl1 = "http://localhost:8080/api/auth/photo/user";    
    let headers1 = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem("token")});
    return this.http.post<Photo[]>(tokenUrl1, JSON.stringify(user), {headers: headers1});
  }

  updatePhoto(photo: Photo) {
    let tokenUrl1 = "http://localhost:8080/api/auth/photo/update";
    let headers1 = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem("token")});
    return this.http.post<Photo[]>(tokenUrl1, JSON.stringify(photo), {headers: headers1});
  }

}
