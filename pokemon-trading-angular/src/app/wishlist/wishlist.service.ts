import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Wishlist } from './wishlist';
import { environment } from 'src/environments/environment';
import { CookieService } from 'ngx-cookie-service';

@Injectable({providedIn: 'root'})
export class WishlistService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient,
              private cookieService:CookieService){}

  public getWishlist(): Observable<Wishlist[]> {
    let cookie = this.cookieService.get("userinfo");
    
    const headers = {'content-type':"application/json"};
    let body = `{
                  "userinfo":${cookie}
                }`
    // console.log("is this thing on?");
    return this.http.post<Wishlist[]>(`${this.apiServerUrl}/wishlist/view`,body,{"headers":headers});

  }

  public deleteWishlist(wishlistId: number): Observable<void> {
    let cookie = this.cookieService.get("userinfo");
    
    const headers = {'content-type':"application/json"};
    let body = `{
                  "userinfo":${cookie}
                }`
    return this.http.post<void>(`${this.apiServerUrl}/wishlist/delete/delete/${wishlistId}`,body,{'headers':headers});
  }
}