import { Component, OnInit } from '@angular/core';
import { NgProgress } from 'ngx-progressbar';
import { Observable } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { ProgressBarService } from '../../services/progress-bar.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private progress: NgProgress, public progressBar: ProgressBarService, private authService: AuthService) { }

  LoginStatus$ : Observable<boolean>;

  ngOnInit(): void {
    this.progressBar.progressRef = this.progress.ref('progressBar');
    this.LoginStatus$ = this.authService.isLogIn;
  }

  public logout():void{
    this.deleteAllCookies();
    window.location.replace("http://localhost:4200");
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
}
