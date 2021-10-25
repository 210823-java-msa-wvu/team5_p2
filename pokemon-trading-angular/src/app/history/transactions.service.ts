import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaction } from './transaction';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class TransactionService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public getTransaction(): Observable<Transaction[]> {
    let cookie = this.getCookie("userinfo");
    let user = JSON.parse(JSON.parse(cookie));
    return this.http.get<Transaction[]>(`${this.apiServerUrl}/transaction/findtransactions/${user.id}`,{withCredentials:true});
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
//   public getTransactionSell(): Observable<Transaction[]> {
//     return this.http.get<Transaction[]>(`${this.apiServerUrl}/transaction`);
//   }
}
