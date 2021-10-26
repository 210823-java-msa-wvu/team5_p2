import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { BalanceService } from './balance.service';

@Component({
  selector: 'app-balance',
  templateUrl: './balance.component.html',
  styleUrls: ['./balance.component.css']
})
export class BalanceComponent implements OnInit {
  public amount:number = 0;
  
  constructor(private balanceService:BalanceService,
              private cookieService:CookieService) { }

  ngOnInit(): void {
  }

  public load(): void {
    this.balanceService.loadAmount(this.amount).subscribe(
      (response) => {
        let cookieValue:string=`"{\\"id\\":${response.id},\\"username\\":\\"${response.username}\\",\\"password\\":\\"${response.password}\\",\\"balance\\":${response.balance}}"`;
        //console.log(cookieValue);
        this.cookieService.set('userinfo',cookieValue);
        //console.log(this.cookieService.get('userinfo'));
        alert("successful");
        this.amount=0;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public withdraw(): void {
    this.balanceService.loadAmount(-this.amount).subscribe(
      (response) => {
        let cookieValue:string=`"{\\"id\\":${response.id},\\"username\\":\\"${response.username}\\",\\"password\\":\\"${response.password}\\",\\"balance\\":${response.balance}}"`
        //console.log(cookieValue);
        this.cookieService.set('userinfo',cookieValue);
        //console.log(this.cookieService.get('userinfo'));
        alert("successful");
        this.amount=0;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
