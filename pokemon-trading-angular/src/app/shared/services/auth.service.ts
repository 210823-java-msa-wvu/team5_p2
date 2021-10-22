import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public login(model: any) {
    return this.http.post(`${this.apiServerUrl}/front/login`, model, {withCredentials:true})
    .pipe(
      map((response: any) => {
        const user = response;
      })
    );
  }


  // httpOptions = {
  //   headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
   
  // withCredentials: true, 
  // observe: 'response' as 'response'
  // };
  // public login(model:any){
  //   console.log('contacting server at '+ this.apiServerUrl + '/front/login' +" with user data "+ model+ " with httpOptions "+ this.httpOptions.withCredentials + ","+ this.httpOptions.headers ); 

  //   let body = JSON.stringify(model);
  //   return this.http.post(`${this.apiServerUrl}/front/login`, body,this.httpOptions).pipe(
  //     map((response: any) => {
  //       const user = response;
  //     })
  //   );
  // }  


  public register(model: any) {
    return this.http.post(`${this.apiServerUrl}/front/create`, model);
  }

}
