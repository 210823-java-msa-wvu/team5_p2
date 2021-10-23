import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Pokemon } from '../pokemon/pokemon';
import { PokemonService } from '../pokemon/pokemon.service';
import { Deal } from './main-menu';
import { MainMenuService } from './main-menu.service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent implements OnInit {
  closeResult:string;
  public sellType:number;
  public deals:Deal[];
  public pokemons:Pokemon[];

  constructor(private mainMenuService:MainMenuService,
              private pokemonService:PokemonService,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getPokemons();
    this.getDeals();
  }

  public getPokemons(): void {
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

  public getDeals(): void {
    this.mainMenuService.getDeals().subscribe(
      (response: Deal[]) => {
        this.deals = response;
        //console.log(this.deals);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onSubmit(f:NgForm):void{
    console.log(JSON.stringify(f.value));
  }

  public doBuy(deal:Deal):void{
    this.mainMenuService.buyItem(deal).subscribe(
      (response:void)=>{
        window.location.reload();
      },
      (error: HttpErrorResponse)=>{
        if(error.status == HttpStatusCode.Forbidden) alert("forbidden: load more balance!");
        else alert("an unknown error has occured, email admin.");
      }
    )
  }

  public addToWishList(deal:Deal):void{
    this.mainMenuService.addWishList(deal).subscribe(
      (response:void)=>{
        console.log(response);
        alert(`successful, ${deal.pokeId.name} has been added to your wishlist.`);
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    )
  }

  public openModal(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }
  
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  public searchDeals(key: string): void {
    console.log(key);
    const results: Deal[] = [];
    for (const deal of this.deals) {
      if (deal.pokeId.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
      ) {
        results.push(deal);
      }
    }
    this.deals = results;
    if (results.length === 0 || !key) {
      this.getDeals();
    }
  }

  public doNothing():void{
    
  }

}
