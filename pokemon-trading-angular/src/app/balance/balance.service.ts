import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../main-menu/main-menu';

@Injectable({
  providedIn: 'root'
})
export class BalanceService {

  private url = environment.apiBaseUrl;
  
  constructor(private http: HttpClient,
              private cookieService: CookieService) { }

  public loadAmount(amount:number): Observable<User>{
    const headers = {'content-type':"application/json"};
    let cookie = this.cookieService.get('userinfo');
    let body =     `{
                      "userinfo":${cookie},
                      "amount":"${amount}"
                    }`
    //console.log(body);
<<<<<<< HEAD
    return this.http.post<void>(`${this.url}/user/put/load`,body,{'headers':headers,withCredentials:true});
=======
    return this.http.post<User>(`${this.url}/user/put/load`,body,{'headers':headers});
>>>>>>> ec2refactor
  }
}
