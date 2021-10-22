import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Wishlist } from './wishlist';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class WishlistService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public getWishlist(): Observable<Wishlist[]> {
    // return this.http.get<Wishlist[]>(`${this.apiServerUrl}/wishlist/view`);
    return this.http.get<Wishlist[]>(`${this.apiServerUrl}/wishlist/view`,{withCredentials:true});
  }

//   public addWishlist(wishlist: Wishlist): Observable<Wishlist> {
//     return this.http.post<Wishlist>(`${this.apiServerUrl}/wishlist/add`, wishlist);
//   }

  public deleteWishlist(wishlistId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/wishlist/delete/${wishlistId}`);
  }
}