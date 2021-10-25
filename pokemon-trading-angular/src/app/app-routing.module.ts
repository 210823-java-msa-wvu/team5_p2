import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BalanceComponent } from './balance/balance.component';
import { MainMenuComponent } from './main-menu/main-menu.component';
import { HomeComponent } from './pages/home/home.component';
import { SubmitDealComponent } from './submit-deal/submit-deal.component';
import { WishlistComponent } from './wishlist/wishlist.component';



const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'main', component: MainMenuComponent},
  { path: 'sell', component: SubmitDealComponent },
  { path: 'balance', component: BalanceComponent },
  { path: 'wishlist', component: WishlistComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
