import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainMenuComponent } from './main-menu/main-menu.component';
import { HomeComponent } from './pages/home/home.component';
import { SubmitDealComponent } from './submit-deal/submit-deal.component';



const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'main', component: MainMenuComponent},
  { path: 'sell', component: SubmitDealComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
