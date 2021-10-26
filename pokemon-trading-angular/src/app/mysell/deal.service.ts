import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Deal } from './deal';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class DealService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public getDeal(): Observable<Deal[]> {

    let cookie = this.getCookie("userinfo");
    let user = JSON.parse(JSON.parse(cookie));
    return this.http.get<Deal[]>(`${this.apiServerUrl}/user/mysell/${user.id}`);
  }

//helper function
  // the cookie or `null`, if the key is not found.
  private getCookie(name: string): string|null {
    const nameLenPlus = (name.length + 1);
    return document.cookie
      .split(';')
      .map(c => c.trim())
      .filter(cookie => {
        return cookie.substring(0, nameLenPlus) === `${name}=`;
      })
      .map(cookie => {
        return decodeURIComponent(cookie.substring(nameLenPlus));
      })[0] || null;
  }


  public deleteDeal(DealId: number): Observable<void> {
    return this.http.post<void>(`${this.apiServerUrl}/deal/delete/delete/${DealId}`,{withCredentials:true});
  }
}
   


