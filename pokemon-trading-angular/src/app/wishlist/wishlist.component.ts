import { Component, OnInit } from '@angular/core';
import { Pokemon } from '../pokemon/pokemon';
import { PokemonService } from '../pokemon/pokemon.service';
import { Wishlist } from './wishlist';
import { WishlistService } from './wishlist.service';
import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {

  public pokemons:Pokemon[];
  public wishlists:Wishlist[];
  constructor(private pokemonService:PokemonService,
              private wishlistSevice:WishlistService) { }

  ngOnInit(): void {

  }
// get wishlists
public getWishlists(): void {
  this.wishlistSevice.getWishlist().subscribe(
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
