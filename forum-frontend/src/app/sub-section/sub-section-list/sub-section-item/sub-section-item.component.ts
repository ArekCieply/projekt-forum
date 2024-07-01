import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/auth.service';
import { HttpService } from '../../../shared/http.service';
import { Sub_Section } from '../../../shared/sub-section.model';
import { User } from '../../../shared/user.model';

@Component({
  selector: 'app-sub-section-item',
  templateUrl: './sub-section-item.component.html',
  styleUrls: ['./sub-section-item.component.css']
})
export class SubSectionItemComponent implements OnInit {

  @Input() sub_section!: Sub_Section;
  @Input() index!: number;
  @Input() sectionId!: number;
  logedUser!: User;
  display = false;
  constructor(private authService: AuthService, private httpService: HttpService) { }

  ngOnInit(): void {
    this.logedUser = this.authService.getCurrentUser();
  }

  deleteSubSection() {
    this.sub_section.userId=this.authService.getCurrentUser().id!;
    this.sub_section.sectionId=this.sectionId;
    this.httpService.deleteSub_Section(this.sub_section)
  }

  editSubSection() {
    this.display = !this.display;
  }

  close(i: boolean) {
    this.display = false;
  }
}
