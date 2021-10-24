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

}
