import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from 'ngx-alerts';
import { AuthService } from 'src/app/shared/services/auth.service';
import { ProgressBarService } from 'src/app/shared/services/progress-bar.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService, public progressBar: ProgressBarService, private alertService: AlertService, private route: Router) { }

  ngOnInit() {
  }

  onSubmit(f: NgForm) {
    this.alertService.info('Checking User Info');
    this.progressBar.startLoading();

    const loginObserver = {
      next: x => {
        this.progressBar.setSuccess();
        console.log('User logged in');
        this.alertService.success('Logged In');
        this.progressBar.completeLoading();
        this.route.navigate(['/main']);
      },
      error: err => {
        this.progressBar.setError();
        console.log(err);
        this.alertService.danger('Unable to Login');
        this.progressBar.completeLoading();
      }
    };

    this.authService.login(f.value).subscribe(loginObserver);
  }

}