import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SubmitDealService {
  private url = environment.apiBaseUrl;

  constructor(private http: HttpClient,
              private cookieService:CookieService) { }

  public createSell(form:NgForm): Observable<void>{
    const headers = {'content-type':"application/json"};
    let cookie = this.cookieService.get("userinfo");

    let body = form.value.type<3? `{
                  "description":"${form.value.description}",
                  "type":${form.value.type},
                  "expire_date":"${form.value.expire_date}",
                  "pokeid":${form.value.item},
                  "price":${form.value.price},
                  "userinfo":${cookie}}`
                  :
                  `{
                    "description":"${form.value.description}",
                    "type":${form.value.type},
                    "expire_date":"${form.value.expire_date}",
                    "pokeid":${form.value.item},
                    "trade_for":${form.value.trade_for},
                    "userinfo":${cookie}}`
    //console.log(body);
    return this.http.post<void>(`${this.url}/deal/sell`,body,{'headers':headers});
  }

}
