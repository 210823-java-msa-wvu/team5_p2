import { Component, OnInit } from '@angular/core';
import { Pokemon } from '../pokemon/pokemon';
import { PokemonService } from '../pokemon/pokemon.service';
import { Deal } from './deal';
import { DealService } from './deal.service';
import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';

@Component({
  selector: 'app-mysell',
  templateUrl: './mysell.component.html',
  styleUrls: ['./mysell.component.css']
})
export class MysellComponent implements OnInit {

  public pokemons:Pokemon[];
  public deals:Deal[];
  constructor(private pokemonService:PokemonService,
              private dealSevice:DealService) { }

  ngOnInit(): void {
this.getDeals();

  }
// get deals
public getDeals(): void {
  this.dealSevice.getDeal().subscribe(
    (response: Deal[]) => {
      this.deals = response;

      console.log(this.deals[0].pokeId);
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  );
  }

public deleteDeals(Id:number) {
  this.dealSevice.deleteDeal(Id).subscribe(
    (response) => {
     
      location.reload();
    },
    (error: HttpErrorResponse) => {
      alert(error.message);

    }
  );
}
}



