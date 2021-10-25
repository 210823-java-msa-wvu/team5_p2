import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { BalanceService } from './balance.service';

@Component({
  selector: 'app-balance',
  templateUrl: './balance.component.html',
  styleUrls: ['./balance.component.css']
})
export class BalanceComponent implements OnInit {
  public amount:number = 0;
  
  constructor(private balanceService:BalanceService) { }

  ngOnInit(): void {
  }

  public load(): void {
    this.balanceService.loadAmount(this.amount).subscribe(
      (response) => {
        alert("successful");
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public withdraw(): void {
    this.balanceService.loadAmount(-this.amount).subscribe(
      (response) => {
        alert("successful");
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
