import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(
    private httpClient: HttpClient
  ) { }

  public getUsers(tokenKey: string): Observable<any>{
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Token', tokenKey);

    return this.httpClient.get('http://localhost:8080/users', {
      responseType: 'json',
      headers: new HttpHeaders().set('Token', tokenKey)});
  }

  public getUser(tokenKey: string, id: bigint): Observable<any>{
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Token', tokenKey);

    return this.httpClient.get('http://localhost:8080/users/' + id, {
      responseType: 'json',
      headers: new HttpHeaders().set('Token', tokenKey)});
  }

  public updateUser(tokenKey: string, id: bigint, username: string, email:string, password:string): Observable<any>{
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Token', tokenKey);

    return this.httpClient.put('http://localhost:8080/users/' + id, {
      username,
      email,
      password
    }, {
      headers: new HttpHeaders().set('Token', tokenKey)});
  }

  public updateUserWithoutPassword(tokenKey: string, id: bigint, username: string, email: string): Observable<any>{
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Token', tokenKey);

    return this.httpClient.put('http://localhost:8080/users/' + id, {
      username,
      email
    }, {
      headers: new HttpHeaders().set('Token', tokenKey)});
  }

  public deleteUser(tokenKey: string, id: bigint): Observable<any>{
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Token', tokenKey);

    return this.httpClient.delete('http://localhost:8080/users/' + id, {
      headers: new HttpHeaders().set('Token', tokenKey)});
  }
}
