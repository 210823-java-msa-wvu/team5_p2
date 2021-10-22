import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
// import { Component } from './wishlist';
import { Wishlist } from './wishlist/wishlist';
import { WishlistService } from './wishlist/wishlist.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'pokemon-trading-angular';
  public wishlists: Wishlist[];

  constructor(private wishlistService: WishlistService){}

    ngOnInit(){
        this.getWishlists();
    }
  
  public getWishlists(): void {
    this.wishlistService.getWishlist().subscribe(
      (response: Wishlist[]) => {
        this.wishlists = response;
        console.log(this.wishlists);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
