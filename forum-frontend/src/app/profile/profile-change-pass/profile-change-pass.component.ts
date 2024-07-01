import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AuthService } from '../../auth/auth.service';
import { HttpService } from '../../shared/http.service';

@Component({
  selector: 'app-profile-change-pass',
  templateUrl: './profile-change-pass.component.html',
  styleUrls: ['./profile-change-pass.component.css']
})
export class ProfileChangePassComponent implements OnInit {
  @Output() displayEvent = new EventEmitter<boolean>();
  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  save(oldPass: string, newPass: string) {
    this.authService.changePass(this.authService.getCurrentUser().id!, oldPass, newPass);
    this.displayEvent.emit(false);
  }
  

}
