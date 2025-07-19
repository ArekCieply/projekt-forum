import { FacebookLoginProvider, GoogleLoginProvider, SocialAuthService, SocialUser, SocialLoginModule } from '@abacritt/angularx-social-login';
import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
  choice!: string;
  socialUser!: SocialUser;
  err!: string;
  display = false;

  constructor(private authService: AuthService,
    private socialAuthService: SocialAuthService
  ) { }

  submit(username: string, pass: string) {
    if (this.choice == "login") {
      this.authService.loginUser(username, pass)
    } else if (this.choice == "register") {
      this.authService.registerUser(username, pass)
    }
  }

  facebookSignin(): void {
    this.socialAuthService.signIn(FacebookLoginProvider.PROVIDER_ID);
    this.authService.facebookLogin(this.socialUser.name, this.socialUser.authToken)
  }

  googleSignin(): void {
  }

   showQR() {
    this.display = !this.display;
  }

  ngOnInit(): void {
    this.socialAuthService.authState.subscribe((user) => {
      this.socialUser = user;
      console.log(this.socialUser);
      if (this.socialUser.provider == "GOOGLE") {
        this.authService.googleLogin(this.socialUser.name, this.socialUser.idToken)
      }
    });
    this.authService.err.subscribe(
      (err: string) => {
        this.err = err
      }
    )
  }

}
