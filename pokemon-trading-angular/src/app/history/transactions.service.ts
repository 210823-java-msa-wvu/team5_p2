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
    return this.http.get<Transaction[]>(`${this.apiServerUrl}/mysell/{id}`);
  }

//   public getTransactionSell(): Observable<Transaction[]> {
//     return this.http.get<Transaction[]>(`${this.apiServerUrl}/transaction`);
//   }
}
