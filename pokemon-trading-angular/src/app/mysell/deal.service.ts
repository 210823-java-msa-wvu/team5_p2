import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Deal } from './deal';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class DealService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public getDeal(): Observable<Deal[]> {
    return this.http.get<Deal[]>(`${this.apiServerUrl}/deal/see`);
  }
}

// //   public addWishlist(wishlist: Wishlist): Observable<Wishlist> {
// //     return this.http.post<Wishlist>(`${this.apiServerUrl}/wishlist/add`, wishlist);
// //   }

//   public deleteWishlist(wishlistId: number): Observable<void> {
//     return this.http.delete<void>(`${this.apiServerUrl}/wishlist/delete/${wishlistId}`);
//   }
// }