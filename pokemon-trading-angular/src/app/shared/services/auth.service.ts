import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiServerUrl = environment.apiBaseUrl;

  public loginStatus  = new BehaviorSubject<boolean>(this.checkLoginStatus());

  constructor(private http: HttpClient) { }

  public login(model: any): Observable <User>
  {
    return this.http.post<User>(`${this.apiServerUrl}/front/login`, model);
  }

  public register(model: any) {
    return this.http.post(`${this.apiServerUrl}/front/create`, model);
  }

  checkLoginStatus() : boolean {
    if (this.getCookie("userinfo") == null) {
      return false;
    }
    else {
      return true;
    }
    
  }

  get isLogIn() {
    return this.loginStatus.asObservable();
  }

  public getCookie(name: string): string|null {
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


  // New cookies method



}

export class User {
  id: number;
  username: string;
  password: string;
  balance: number;
}
