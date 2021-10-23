import { Component, OnInit } from '@angular/core';
import { Pokemon } from '../pokemon/pokemon';
import { PokemonService } from '../pokemon/pokemon.service';
import { Wishlist } from './wishlist';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {

  public pokemons:Pokemon[];
  constructor(private pokemonService:PokemonService) { }

  ngOnInit(): void {
  }

  
}
