import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Pokemon } from '../pokemon/pokemon';
import { Deal, User } from './main-menu';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class MainMenuService {
  private url = environment.apiBaseUrl;


  constructor(private http: HttpClient,
              private cookieService: CookieService) { }

  public getDeals(): Observable<Deal[]>{

    return this.http.get<Deal[]>(`${this.url}/deal`);
  }

  public buyItem(deal:Deal): Observable<User>{
    let cookie = this.cookieService.get("userinfo");
    
    const headers = {'content-type':"application/json"};
    let body = `{
                  "userinfo":${cookie}
                }`
    return this.http.post<User>(`${this.url}/user/buy/${deal.id}`,body,{'headers':headers});
  }

  public bidItem(amount:number,dealId:number): Observable<User>{
    let cookie = this.cookieService.get("userinfo");

    const headers = {'content-type':"application/json"};
    let body=`{
                "userinfo":${cookie},
                "amount":${amount}
              }`
    return this.http.post<User>(`${this.url}/user/put/bid/${dealId}`,body,{'headers':headers});
  }

  public getInfo():Observable<User>{
    let cookie = this.cookieService.get("userinfo");
    let user = JSON.parse(JSON.parse(cookie));
    //console.log(user);
    return this.http.get<User>(`${this.url}/user/getinfo/${user.id}`);
  }
  /*
  public createSell(form:NgForm): Observable<void>{
    const headers = {'content-type':"application/json"};
    let body = form.value.type<3? `{
                  "description":"${form.value.description}",
                  "type":${form.value.type},
                  "expire_date":"${form.value.expire_date}",
                  "pokeid":${form.value.item},
                  "price":${form.value.price}}`
                  :
                  `{
                    "description":"${form.value.description}",
                    "type":${form.value.type},
                    "expire_date":"${form.value.expire_date}",
                    "pokeid":${form.value.item},
                    "trade_for":${form.value.trade_for}}`
    console.log(body);
    return this.http.post<void>(`${this.url}/deal/sell`,body,{'headers':headers,withCredentials:true});
  }*/

  public addWishList(deal:Deal): Observable<void>{
    const headers = {'content-type':'application/json'};
    let cookie = this.cookieService.get("userinfo");
    let user = JSON.parse(JSON.parse(cookie));
    let body = `{
                  "userid":{"id":${user.id}},
                  "pokeid":{"id":${deal.pokeId.id}}
                }`
    return this.http.post<any>(`${this.url}/wishlist/add`,body,{'headers':headers});
  }

  public addDeal(description:string,type:number,expire_date:string,
                 pokeid:number,price:number,trade_for:number): Observable<void>{
    const headers = {'content-type':'application/json'};
    let body = `{
                  "description":${description},
                  "type":${type},
                  "expire_date":${expire_date},
                  "pokeid":${pokeid},
                  "price":${price},
                  "trade_for":${trade_for}`;
    return this.http.post<void>(`${this.url}/deal/sell`,body,{'headers':headers});
  }

  public getWishlistNotify(): Observable<Pokemon[]> {
    // console.log("is this thing on?");
    let cookie = this.cookieService.get("userinfo");
    
    const headers = {'content-type':"application/json"};
    let body = `{
                  "userinfo":${cookie}
                }`
    return this.http.post<Pokemon[]>(`${this.url}/user/notify`,body,{'headers':headers});
  }

}
