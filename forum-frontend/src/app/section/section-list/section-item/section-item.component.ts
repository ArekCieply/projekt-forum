import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/auth.service';
import { HttpService } from '../../../shared/http.service';
import { Section } from '../../../shared/section.model';
import { User } from '../../../shared/user.model';

@Component({
  selector: 'app-section-item',
  templateUrl: './section-item.component.html',
  styleUrls: ['./section-item.component.css']
})
export class SectionItemComponent implements OnInit {

  @Input() section!: Section;
  @Input() index!: number;
  logedUser!: User;
  display = false;
  constructor(private authService: AuthService, private httpService: HttpService) { }

  ngOnInit(): void {
    if(this.authService.getCurrentUser()!==null){
    this.logedUser = this.authService.getCurrentUser();
    }
  }

  deleteSection() {
    this.section.userId=this.authService.getCurrentUser().id!;
    this.httpService.deleteSection(this.section)
  }
  editSection() {
    this.display = !this.display;
  }

  close(i: boolean) {
    this.display = false;
  }
}
