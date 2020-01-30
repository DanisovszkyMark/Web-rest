import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor(
    private httpClient: HttpClient
  ) { }

  public login(usernameOrEmail: string, password: string): Observable<any>{
    return this.httpClient.post('http://localhost:8080/auth/login', {
      username: usernameOrEmail,
      password,
    }, { responseType: 'text' }).pipe(
      tap(response => localStorage.setItem('token', response)),
    );
  }

  public registration(username: string, email: string, password: string): Observable<any>{
    return this.httpClient.post('http://localhost:8080/auth/reg', {
      username,
      email,
      password
    });
  }

  public logout(tokenKey: string): Observable<any>{
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Token', tokenKey);

    return this.httpClient.delete("http://localhost:8080/auth/logout", {
      headers: new HttpHeaders().set('Token', tokenKey),
    });
  }

  public activation(activationKey: string): Observable<any>{
    return this.httpClient.put("http://localhost:8080/auth/activation/" + activationKey, null);
  }

  public block(id: bigint): Observable<any>{
    return this.httpClient.put("http://localhost:8080/auth/block/" + id, null);
  }
}
