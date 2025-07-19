import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-auth-qr',
  templateUrl: './auth-qr.component.html',
  styleUrls: ['./auth-qr.component.css']
})
export class AuthQrComponent implements OnInit {
QRData: string = '';
  constructor(private authService: AuthService) { }

  ngOnInit(): void {
      const token = this.generateId();
    this.QRData = JSON.stringify({
      token,
      //timestamp: new Date().toISOString()
    });
    this.authService.QRStart(token)
  }


   generateId(): string {
    const array = new Uint32Array(8);
    window.crypto.getRandomValues(array);
    return Array.from(array, num => num.toString(16)).join('-');
  }
}
