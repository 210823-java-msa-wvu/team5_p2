import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Deal } from './deal';
import { environment } from 'src/environments/environment';
import { CookieService } from 'ngx-cookie-service';

@Injectable({providedIn: 'root'})
export class DealService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient,
              private cookieService:CookieService){}

  public getDeal(): Observable<Deal[]> {

    let cookie = this.cookieService.get("userinfo");
    let user = JSON.parse(JSON.parse(cookie));
    return this.http.get<Deal[]>(`${this.apiServerUrl}/user/mysell/${user.id}`);
  }


  public deleteDeal(DealId: number): Observable<void> {
    let cookie = this.cookieService.get("userinfo");
    
    const headers = {'content-type':"application/json"};
    let body = `{
                  "userinfo":${cookie}
                }`
    return this.http.post<void>(`${this.apiServerUrl}/deal/delete/delete/${DealId}`,body,{'headers':headers});
  }
}


