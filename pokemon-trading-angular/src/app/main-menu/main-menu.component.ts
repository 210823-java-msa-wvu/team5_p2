import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Pokemon } from '../pokemon/pokemon';
import { PokemonService } from '../pokemon/pokemon.service';

import { Deal, User } from './main-menu';
import { MainMenuService } from './main-menu.service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgForm } from '@angular/forms';
import { environment } from 'src/environments/environment';
import { Wishlist } from '../wishlist/wishlist';
import { AlertService } from 'ngx-alerts';

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent implements OnInit {
  closeResult:string;

  currentUser:User;
 
  currentDeal:number;
  typeTable:string[]=['','Buy Now','Auction','Trade'];
  panelOpenState = false;

  public sellType:number;
  public deals:Deal[];
  public pokemons:Pokemon[];
  public wishlists:Wishlist[];
  public pokeWishlists:Pokemon[];

  constructor(private mainMenuService:MainMenuService,
              private pokemonService:PokemonService,
              private modalService: NgbModal,
              private alertService: AlertService) { }

  ngOnInit(): void {
    this.getPokemons();
    this.getDeals();
    this.populateUser();
    this.wishlistNotify();
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

  // public onSubmit(f:NgForm):void{

  //   this.mainMenuService.createSell(f).subscribe(
  //     (response)=>{
  //       alert(`succeed, item created on market.`);
  //       window.location.reload();
  //     },
  //     (error:HttpErrorResponse)=>{
  //       alert(error.message);
  //     }
  //   )

  //   console.log(JSON.stringify(f.value));

  // }

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

  public openModal(content,deal:Deal) {
    this.currentDeal=deal.id;
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


  public populateUser():void{
    let cookie = this.getCookie("userinfo");
    this.currentUser = JSON.parse(JSON.parse(cookie));
  }

  public logout():void{
    this.deleteAllCookies();
    window.location.replace(`${environment.frontendBaseUrl}`);
  }

  public onSubmit(f:NgForm){
    this.mainMenuService.bidItem(f.value.bid_amount,this.currentDeal).subscribe(
      (response:void)=>{
        console.log(response);
        alert(`successful, you became the highest bidder.`);
        window.location.reload();
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
      }
    );
  }

  public doNothing():void{
    
  }



  //helper function
  // the cookie or `null`, if the key is not found.
  private getCookie(name: string): string|null {
    const nameLenPlus = (name.length + 1);
    return document.cookie
      .split(';')
      .map(c => c.trim())
      .filter(cookie => {
        return cookie.substring(0, nameLenPlus) === `${name}=`;
      })
      .map(cookie => {
        return decodeURIComponent(cookie.substring(nameLenPlus));
      })[0] || null;
  }

  private deleteAllCookies() {
    var cookies = document.cookie.split(";");

    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
  }
//This is a variable
  searchText;

  public wishlistNotify(): void {
    this.mainMenuService.getWishlistNotify().subscribe(
      (response: Pokemon[]) => {
        this.pokeWishlists = response;
        console.log(this.pokeWishlists);
        for (var val of response) {
          this.alertService.success('Your pokemon from wishlist ' + val.name + ' is on market now!');
        }

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
