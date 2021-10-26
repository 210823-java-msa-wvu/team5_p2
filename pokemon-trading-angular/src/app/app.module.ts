import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import {MatNativeDateModule} from '@angular/material/core';
import {MatExpansionModule} from '@angular/material/expansion';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PokemonComponent } from './pokemon/pokemon.component';
import { PokemonService } from './pokemon/pokemon.service';
import { MainMenuComponent } from './main-menu/main-menu.component';
import { MainMenuService } from './main-menu/main-menu.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthModule } from './auth/auth.module';
import { SharedModule } from './shared/shared.module';
import { HomeComponent } from './pages/home/home.component';
import { HistoryComponent } from './history/history.component';
import { SubmitDealComponent } from './submit-deal/submit-deal.component';
import { BalanceComponent } from './balance/balance.component';
import { WishlistComponent } from './wishlist/wishlist.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { MysellComponent } from './mysell/mysell.component';
import { CookieService } from 'ngx-cookie-service';




@NgModule({
  declarations: [
    AppComponent,
    PokemonComponent,
    HomeComponent,
    MainMenuComponent,
    WishlistComponent,
    MysellComponent,
    HistoryComponent,
    SubmitDealComponent,
    BalanceComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    MatNativeDateModule,
    MatExpansionModule,
    BrowserAnimationsModule,
    AuthModule,
    SharedModule,
    Ng2SearchPipeModule

  ],
  providers: [PokemonService,MainMenuService, CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
