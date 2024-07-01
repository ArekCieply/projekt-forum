import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { ThemeService } from '../shared/theme.service';
import { User } from '../shared/user.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  subscription!: Subscription;
  currentuser!: User;
  authenticated = false;
  display = false;
  constructor(private authservice: AuthService, private themeService: ThemeService) { }


  ngOnInit(): void {
    this.authenticated = this.authservice.getAuthenticated();
    this.subscription = this.authservice.authenticatedChanged.subscribe(
      (authenticated: boolean) => {
        this.authenticated = authenticated
      }
    )

    this.currentuser = this.authservice.getCurrentUser();
    this.subscription = this.authservice.userChanged.subscribe(
      (user: User) => {
        this.currentuser = user
      }
    )
  }
  toggle(){
    this.display=!this.display;
  }

  logout() {
    this.authservice.logOut();
  }

  dayTheme() {
    this.themeService.setDayTheme();
  }
  nightTheme() {
    this.themeService.setNightTheme();
  }

}
