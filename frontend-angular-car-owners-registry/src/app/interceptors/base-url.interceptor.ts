import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class BaseUrlInterceptor implements HttpInterceptor {

    constructor(
            @Inject('BASE_API_URL') private baseUrl: string) {
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const url = this.baseUrl + (request.url.startsWith('/') ? request.url : ('/' + request.url));
        const newRequest = request.clone({ url });
        return next.handle(newRequest);
    }
}