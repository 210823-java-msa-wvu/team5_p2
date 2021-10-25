import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgProgress } from 'ngx-progressbar';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from '../../services/auth.service';
import { ProgressBarService } from '../../services/progress-bar.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private progress: NgProgress, public progressBar: ProgressBarService, private authService: AuthService, public route: Router) { }

  LoginStatus$ : Observable<boolean>;

  ngOnInit(): void {
    this.progressBar.progressRef = this.progress.ref('progressBar');
    this.LoginStatus$ = this.authService.isLogIn;
  }

  public logout():void{
    this.deleteAllCookies();
    window.location.replace(`${environment.frontendBaseUrl}`);
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

  public checkCookie(): void {
    if(this.authService.getCookie("userinfo") != null )
    {
      this.route.navigate(['/main']);
    }
    else
    {
      this.route.navigate(['']);
    }
    
  }

  searchText;

  // public searchPokemons(key: string): void {
  //   console.log(key);
  //   const results: Pokemon[] = [];
  //   for (const pokemon of this.pokemons) {
  //     if (pokemon.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
  //     ) {
  //       results.push(pokemon);
  //     }
  //   }
  //   this.pokemons = results;
  //   if (results.length === 0 || !key) {
  //     this.getPokemons();
  //   }
  // }
}
