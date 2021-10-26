import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BalanceService {

  private url = environment.apiBaseUrl;
  
  constructor(private http: HttpClient) { }

  public loadAmount(amount:number): Observable<void>{
    const headers = {'content-type':"application/json"};
    let body =     `{
                      "amount":"${amount}"
                    }`
    //console.log(body);
    return this.http.post<void>(`${this.url}/user/put/load`,body,{'headers':headers,withCredentials:true});
  }
}
