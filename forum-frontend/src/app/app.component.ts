import { Component } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { HttpService } from "./shared/http.service";
import { Section } from './shared/section.model';
import { ThemeService } from './shared/theme.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'forum-frontend';
  constructor(private httpService: HttpService, private authService: AuthService, private themeService: ThemeService) {

  }
  ngOnInit(): void {
    this.httpService.getSections();
    this.authService.checkLocalStorage();
   // this.themeService.setDayTheme();
   this.themeService.checkLocalStorage();
  }
}
