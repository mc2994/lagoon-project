import { Injectable } from '@angular/core';
import {
    HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse,HttpErrorResponse
} from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { Observable, throwError  } from 'rxjs';
import { Router } from '@angular/router';
// import { TokenStorage } from './token.storage';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable({
    providedIn: 'root'
  })
export class Interceptor implements HttpInterceptor {

    constructor(private router: Router) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let authReq = req;

        if (localStorage.getItem("token") != null) {
            authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + localStorage.getItem("token")) });
        }
        return next.handle(authReq).pipe(
            map((event: HttpEvent<any>) => {
                if (event instanceof HttpResponse) {
                    console.log('event--->>>', event);
                }
                return event;
            }, catchError((error: HttpErrorResponse) => {
                alert("errrororrrrrrrrrrrr");
                let data = {};
                data = {
                    reason: error && error.error.reason ? error.error.reason : '',
                    status: error.status
                };
               // if(error.status === 404){
                    alert("errrororrrrrrrrrrrr");
                    this.router.navigate(['/login']);
               // }
                return throwError(error);
            })));
    }
}