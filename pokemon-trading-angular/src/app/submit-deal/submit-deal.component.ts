import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Pokemon } from '../pokemon/pokemon';
import { PokemonService } from '../pokemon/pokemon.service';
import { SubmitDealService } from './submit-deal.service';

@Component({
  selector: 'app-submit-deal',
  templateUrl: './submit-deal.component.html',
  styleUrls: ['./submit-deal.component.css']
})
export class SubmitDealComponent implements OnInit {
  public sellType:number;
  public pokemons:Pokemon[];

  constructor(private submitDealService:SubmitDealService,
              private pokemonService:PokemonService) { }

  ngOnInit(): void {
    this.getPokemons();
  }

  private getPokemons(): void {
    this.pokemonService.getPokemons().subscribe(
      (response: Pokemon[]) => {
        this.pokemons = response;
        console.log(this.pokemons);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onSubmit(f:NgForm):void{
    this.submitDealService.createSell(f).subscribe(
      (response)=>{
        alert(`succeed, item created on market.`);
        window.location.reload();
      },
      (error:HttpErrorResponse)=>{
        alert(error.message);
      }
    )
  }
}
