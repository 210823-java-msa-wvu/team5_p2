import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Deal } from './main-menu';

@Injectable({
  providedIn: 'root'
})
export class MainMenuService {
  private url = environment.apiBaseUrl;


  constructor(private http: HttpClient) { }

  public getDeals(): Observable<Deal[]>{
    return this.http.get<Deal[]>(`${this.url}/deal`,{withCredentials:true});
  }

  public buyItem(deal:Deal): Observable<void>{
    return this.http.post<void>(`${this.url}/user/buy/${deal.id}`,null,{withCredentials:true});
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
    let cookie = this.getCookie("userinfo");
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
}
