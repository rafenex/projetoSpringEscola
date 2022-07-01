import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {
    if ((request.url.includes('/api/alunos'))
      || (request.url.includes('/api/professores'))
      || (request.url.includes('/api/cursos'))
      || (request.url.includes('/api/turmas'))) {
      var accessToken = localStorage.getItem('access_token');
      request = request.clone({
        setHeaders: { Authorization: 'Bearer ' + accessToken }
      });
    }
    return next.handle(request);
  }
}
