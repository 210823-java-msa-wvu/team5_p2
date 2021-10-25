import { Component, OnInit } from '@angular/core';
import { Pokemon } from '../pokemon/pokemon';
import { PokemonService } from '../pokemon/pokemon.service';
import { Transaction } from './transaction';
import { TransactionService } from './transactions.service';
import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  public pokemons:Pokemon[];
  public transactions:Transaction[];
  constructor(private pokemonService:PokemonService,
              private transactionService:TransactionService) { }

  ngOnInit(): void {
 this.getTransactions();

  }
// get transactions
public getTransactions(): void {
  this.transactionService.getTransaction().subscribe(
    (response: Transaction[]) => {
      this.transactions = response;
      console.log(this.transactions[0].id);

    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  );
}



}