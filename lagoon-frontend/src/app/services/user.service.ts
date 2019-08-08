import { User } from '../models/user';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { PaginatedObject } from '../utils/paginated-object';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  users: User[];

  constructor(private http: HttpClient) { }

  getAllUsers(page: number, size: number) {
    let params = new HttpParams();
    params = params.append("size", String(size));
    params = params.append("page", String(--page));

    let tokenUrl = "http://localhost:8080/api/auth/user/getAllUsers";
    let headers = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem("token") });
    return this.http.get<PaginatedObject<User>>(tokenUrl, { headers: headers, params: params });
  }

  getUserById(id: string) {
  }

  fetchUsersByName(fullName: string) {
    let tokenUrl = "http://localhost:8080/api/auth/user/fetchUsersByName/" + fullName;
    let headers = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem("token") });
    return this.http.get<PaginatedObject<User>>(tokenUrl, { headers: headers });
  }

  fetchPhotosAync() {
    let tokenUrl = "http://localhost:8080/api/auth/findPhotosAsync/";
    let headers = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem("token") });
    return this.http.get(tokenUrl, { headers: headers });
  }

  getUserByName(userName: string) {
    let tokenUrl = "http://localhost:8080/api/auth/user/userName/" + userName;
    let headers = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem("token") });
    return this.http.get<User>(tokenUrl, { headers: headers });
  }

  updateUser(user: User) {
    let tokenUrl1 = "http://localhost:8080/api/auth/user/update";
    let headers1 = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem("token") });
    return this.http.post<User>(tokenUrl1, JSON.stringify(user), { headers: headers1 });
  }

  downloadPDF() {
    let url = "http://localhost:8080/api/auth/pdf";
    let headers1 = new HttpHeaders({ 'Content-Type': 'application/octet-stream', 'Authorization': 'Bearer ' + localStorage.getItem("token") });
    return this.http.get(url, { headers: headers1, observe: 'response', responseType: 'blob' });
  }

  downloadExcel() {
    let url = "http://localhost:8080/api/auth/excel";
    let headers1 = new HttpHeaders({ 'Content-Type': 'application/octet-stream', 'Authorization': 'Bearer ' + localStorage.getItem("token") });
    return this.http.get(url, { headers: headers1, observe: 'response', responseType: 'blob' });
  }
}
