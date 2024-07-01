import { Injectable } from '@angular/core';
import { day, night, Themes } from './themes';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {

  private active!: Themes;
  private availableThemes: Themes[] = [day, night];
  constructor() { }

  setDayTheme(): void {
    this.setActiveTheme(day);
    localStorage.setItem('theme',JSON.stringify(day));
  }

  setNightTheme(): void {
    this.setActiveTheme(night);
    localStorage.setItem('theme',JSON.stringify(night));
  }


  setActiveTheme(theme: Themes): void {
    this.active = theme;

    Object.keys(this.active.cssVars).forEach(cssVar => {
      document.documentElement.style.setProperty(
        cssVar,
        this.active.cssVars[cssVar]
      );
    });
  }

  checkLocalStorage(){
    let theme: Themes=JSON.parse(localStorage.getItem('theme')!) as Themes;
    this.setActiveTheme(theme);
    if(theme==undefined){
      this.setActiveTheme(day);
    }
  }
}
