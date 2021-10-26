import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaction } from './transaction';
import { environment } from 'src/environments/environment';
import { CookieService } from 'ngx-cookie-service';

@Injectable({providedIn: 'root'})
export class TransactionService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient,
              private cookieService: CookieService){}

  public getTransaction(): Observable<Transaction[]> {
    let cookie = this.cookieService.get("userinfo");
    let user = JSON.parse(JSON.parse(cookie));
    return this.http.get<Transaction[]>(`${this.apiServerUrl}/transaction/findtransactions/${user.id}`);
  }

}
