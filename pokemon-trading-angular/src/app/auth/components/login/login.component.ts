import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from 'ngx-alerts';
import { AuthService, User } from 'src/app/shared/services/auth.service';
import { ProgressBarService } from 'src/app/shared/services/progress-bar.service';
import { Location } from '@angular/common';
import { environment } from 'src/environments/environment';
import { CookieService } from 'ngx-cookie-service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private cookieValue: string;
  Obj: User;
  

  constructor(
    private authService: AuthService, 
    public progressBar: ProgressBarService, 
    private alertService: AlertService, public route: Router, 
    public _location: Location, 
    private cookieService: CookieService
  ) { }

  ngOnInit() {
    this.cookieValue = this.cookieService.get('userinfo');
  }

  onSubmit(f: NgForm) {
    this.authService.checkLoginStatus;
    this.alertService.info('Checking User Info');
    this.progressBar.startLoading();

    // const loginObserver = {
    //   next: x => {
    //     console.log(this.Obj);
    //     let userbody: string = "{\"id\":" + this.Obj.id + ",\"username\":\"" + this.Obj.username + "\",\"password\":\"" + this.Obj.password + "\",\"balance\":" + this.Obj.balance + "}";
    //     this.cookieService.set('userinfo', userbody);


    //     console.log(this.cookieService.get('userinfo'));
    //     this.progressBar.setSuccess();
    //     // console.log('User logged in');
    //     this.alertService.success('Logged In');
    //     this.progressBar.completeLoading();
    //     //this.route.navigate(['/main']);
    //     // window.location.replace(`${environment.frontendBaseUrl}/main`);
    //   },
    //   error: err => {
    //     this.progressBar.setError();
    //     console.log(err);
    //     this.alertService.danger('Unable to Login');
    //     this.progressBar.completeLoading();
    //   }
    // };

    this.authService.login(f.value).subscribe(
      (response)=>{
        let cookieValue:string=`"{\\"id\\":${response.id},\\"username\\":\\"${response.username}\\",\\"password\\":\\"${response.password}\\",\\"balance\\":${response.balance}}"`;
        console.log(cookieValue);
        this.cookieService.set('userinfo',cookieValue);
        console.log(this.cookieService.get('userinfo'));
        this.progressBar.setSuccess();
        // console.log('User logged in');
        this.alertService.success('Logged In');
        this.progressBar.completeLoading();
        //this.route.navigate(['/main']);
        window.location.replace(`${environment.frontendBaseUrl}/main`);
      },
      
      (error: HttpErrorResponse)=>{
        this.progressBar.setError();
        console.log(error);
        this.alertService.danger('Unable to Login');
        this.progressBar.completeLoading();
      }
 
    )

    // this.authService.login(f.value).subscribe(loginObserver);
  }

}