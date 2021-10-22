import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AlertService } from 'ngx-alerts';
import { AuthService } from 'src/app/shared/services/auth.service';
import { ProgressBarService } from 'src/app/shared/services/progress-bar.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private authService: AuthService, public progressBar: ProgressBarService, private alertService: AlertService) { }

  ngOnInit() {
  }

  onSubmit(f: NgForm) {
    this.alertService.info('Working on creating new account');
    this.progressBar.startLoading();
    const registerObserver = {
      next: x => {
        console.log('User created');
        this.progressBar.setSuccess();
        this.alertService.success('Account created, happy trading!');
        this.progressBar.completeLoading();
      },
      error: err => {
        this.progressBar.setError();
        console.log(err);
        this.alertService.danger('Username already exists!');
        this.progressBar.completeLoading();
      }
    };
    this.authService.register(f.value).subscribe(registerObserver);
  }

}
