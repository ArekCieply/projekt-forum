import { HttpClient, HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject, timer, of } from 'rxjs';
import { switchMap, takeUntil, filter, catchError, timeout } from 'rxjs/operators';
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
  private token!: string;
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
      'Authorization': 'Basic ' + btoa(this.getCurrentUser().name + ':' + this.getToken())
    })
  }

  getUrl() {
    return this.configService.getUrl();
  }

  registerUser(name: string, pass: string) {
    this.http.post(this.getUrl() + 'users/register', { name, pass }).subscribe((response) => {
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
    this.http.post(this.getUrl() + 'users/login', { name, pass }).subscribe((response) => {
      console.log(response);
      this.authenticated = true;
      this.currentuser = <User>response;
      this.setUserChanged(this.currentuser);
      this.setAuthenticatedChanged(true);
      this.token = this.currentuser.token!;
      localStorage.setItem('currentuser', JSON.stringify(this.currentuser));
      localStorage.setItem('token', this.currentuser.token!);
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

  facebookLogin(name: string, fbToken: string) {
    this.http.post(this.getUrl() + 'users/login/facebook', { name, fbToken }).subscribe((response) => {
      console.log(response);
      this.authenticated = true;
      this.currentuser = <User>response;
      this.setUserChanged(this.currentuser);
      this.setAuthenticatedChanged(true);
      this.token = this.currentuser.token!;
      localStorage.setItem('currentuser', JSON.stringify(this.currentuser));
      localStorage.setItem('token', this.currentuser.token!);
      localStorage.setItem('authenticated', JSON.stringify(true));
    },
      err => {
        console.log(err);
        this.authenticated = false;
      }
    );
  }

  googleLogin(name: string, googleToken: string) {
    this.http.post(this.getUrl() + 'users/login/google', { name, googleToken }).subscribe((response) => {
      console.log(response);
      this.authenticated = true;
      this.currentuser = <User>response;
      this.setUserChanged(this.currentuser);
      this.setAuthenticatedChanged(true);
      this.token = this.currentuser.token!;
      localStorage.setItem('currentuser', JSON.stringify(this.currentuser));
      localStorage.setItem('token', this.currentuser.token!);
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
    this.http.post(this.getUrl() + 'users/change_pass', { id, oldPass, newPass }, this.httpOptions).subscribe((response) => {
      console.log(response);

    },
      err => {
        console.log(err);

      }
    );
  }

  QRStart(token: string) {
    this.http.post(this.getUrl() + 'users/qr/start', { token }).subscribe((response) => {
      console.log(response);
    },
      (err: HttpErrorResponse) => {
        console.log(err.message);

      }
    );
    this.QRCheck(token)
  }

  QRCheck(token: string) {
    const pollingInterval = 3000;
    const maxPollingTime = 100000;

    timer(0, pollingInterval).pipe(
      takeUntil(timer(maxPollingTime)),
      switchMap(() =>
        this.http.post(this.getUrl() + 'users/qr/check', { token }, { observe: 'response' }).pipe(
          catchError(error => {
            console.error(error.message);
            return of(null);
          })
        )
      ),
      filter((response: HttpResponse<any> | null) => !!response && response.status === 200)
    ).subscribe({
      next: (response) => {
        console.log(response);
        this.saveUserData(<User>response!.body)
      },
      complete: () => console.log('done')
    });


  }

  getCurrentUser() {
    return this.currentuser;
  }
  getAuthenticated() {
    return this.authenticated;
  }
  getToken() {
    return this.token;
  }
  setToken(token: string) {
    this.token = token;
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
    this.data[1] = this.token
    return this.data
  }

  checkLocalStorage() {
    this.currentuser = JSON.parse(localStorage.getItem('currentuser')!) as User;
    this.token = localStorage.getItem('token') as string;
    this.authenticated = JSON.parse(localStorage.getItem('authenticated')!) as boolean;
    this.setUserChanged(this.currentuser);
    this.setAuthenticatedChanged(this.authenticated);
    console.log(this.currentuser)
    console.log(this.authenticated)
    console.log(this.token)
  }

  logOut() {
    localStorage.removeItem('currentuser');
    localStorage.removeItem('token');
    localStorage.removeItem('authenticated');
    this.setUserChanged({} as User);
    this.setAuthenticatedChanged(false);
    this.currentuser = {} as User
    this.token != null
    this.authenticated = false;
  }

  saveUserData(user: User) {
    this.authenticated = true;
    this.currentuser = <User>user;
    this.setUserChanged(this.currentuser);
    this.setAuthenticatedChanged(true);
    this.token = this.currentuser.token!;
    localStorage.setItem('currentuser', JSON.stringify(this.currentuser));
    localStorage.setItem('token', this.currentuser.token!);
    localStorage.setItem('authenticated', JSON.stringify(true));
  }

}
