import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { User } from '../shared/user.model';
import { ConfigService } from '../shared/config.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authenticated = false;
  authenticatedChanged = new Subject<boolean>();
  private currentuser!: User;
  userChanged = new Subject<User>();
  private pass!: string;
  private data!: string[];
  err = new Subject<string>();
  constructor(
    private http: HttpClient,
    private configService: ConfigService,
  ) { }
  httpOptions = {
    headers: new HttpHeaders
  };

  getHeaders() {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(this.getCurrentUser().name + ':' + this.getPass())
    })
  }

  getUrl(){
    return this.configService.getUrl();
  }

  registerUser(name: string, pass: string) {
    this.http.post(this.getUrl()+'users/register', { name, pass }).subscribe((response) => {
      console.log(response);
    },
      (err: HttpErrorResponse) => {
        console.log(err.message);
        this.authenticated = false;
        if (err.status == 409) {
          this.setErr("Zajęta nazwa użytkownika")
        }
      }
    );
  }

  loginUser(name: string, pass: string) {
    this.http.post(this.getUrl()+'users/login', { name, pass }).subscribe((response) => {
      console.log(response);
      this.authenticated = true;
      this.pass = pass;
      this.currentuser = <User>response;
      this.setUserChanged(this.currentuser);
      this.setAuthenticatedChanged(true);
      localStorage.setItem('currentuser', JSON.stringify(this.currentuser));
      localStorage.setItem('pass', this.pass);
      localStorage.setItem('authenticated', JSON.stringify(true));
    },
      (err: HttpErrorResponse) => {
        console.log(err);
        this.authenticated = false;
        if (err.status == 401) {
          this.setErr("Złe dane logowania")
        }
      }
    );
  }

  facebookLogin(name: string, token: string) {
    this.http.post(this.getUrl()+'users/login/facebook', { name, token }).subscribe((response) => {
      console.log(response);
      this.authenticated = true;
      this.pass = token;
      this.currentuser = <User>response;
      this.setUserChanged(this.currentuser);
      this.setAuthenticatedChanged(true);
      localStorage.setItem('currentuser', JSON.stringify(this.currentuser));
      localStorage.setItem('pass', this.pass);
      localStorage.setItem('authenticated', JSON.stringify(true));
    },
      err => {
        console.log(err);
        this.authenticated = false;
      }
    );
  }

  googleLogin(name: string, token: string) {
    this.http.post(this.getUrl()+'users/login/google', { name, token }).subscribe((response) => {
      console.log(response);
      this.authenticated = true;
      this.pass = token;
      this.currentuser = <User>response;
      this.setUserChanged(this.currentuser);
      this.setAuthenticatedChanged(true);
      localStorage.setItem('currentuser', JSON.stringify(this.currentuser));
      localStorage.setItem('pass', this.pass);
      localStorage.setItem('authenticated', JSON.stringify(true));
    },
      err => {
        console.log(err);
        this.authenticated = false;
      }
    );
  }

  changePass(id: number, oldPass: string, newPass: string) {
    this.httpOptions.headers = this.getHeaders()
    this.http.post(this.getUrl()+'users/change_pass', { id, oldPass, newPass }, this.httpOptions).subscribe((response) => {
      console.log(response);

    },
      err => {
        console.log(err);

      }
    );
  }


  getCurrentUser() {
    return this.currentuser;
  }
  getAuthenticated() {
    return this.authenticated;
  }
  getPass() {
    return this.pass;
  }
  setPass(pass: string) {
    this.pass = pass;
  }
  setErr(err: string) {
    this.err.next(err);
  }

  private setUserChanged(user: User) {
    this.userChanged.next(user);
  }

  private setAuthenticatedChanged(authenticated: boolean) {
    this.authenticatedChanged.next(authenticated);
  }

  getAuthData(): string[] {
    this.data[0] = this.currentuser.name
    this.data[1] = this.pass
    return this.data
  }

  checkLocalStorage() {
    this.currentuser = JSON.parse(localStorage.getItem('currentuser')!) as User;
    this.pass = localStorage.getItem('pass') as string;
    this.authenticated = JSON.parse(localStorage.getItem('authenticated')!) as boolean;
    this.setUserChanged(this.currentuser);
    this.setAuthenticatedChanged(this.authenticated);
    console.log(this.currentuser)
    console.log(this.authenticated)
    console.log(this.pass)
  }

  logOut() {
    localStorage.removeItem('currentuser');
    localStorage.removeItem('pass');
    localStorage.removeItem('authenticated');
    this.setUserChanged({} as User);
    this.setAuthenticatedChanged(false);
    this.currentuser = {} as User
    this.pass != null
    this.authenticated = false;
  }

}
