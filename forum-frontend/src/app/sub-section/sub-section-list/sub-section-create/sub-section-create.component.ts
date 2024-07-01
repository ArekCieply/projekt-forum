import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/auth.service';
import { HttpService } from '../../../shared/http.service';
import { Sub_Section } from '../../../shared/sub-section.model';

@Component({
  selector: 'app-sub-section-create',
  templateUrl: './sub-section-create.component.html',
  styleUrls: ['./sub-section-create.component.css']
})
export class SubSectionCreateComponent implements OnInit {
  @Input() sectionId!: number;

  constructor(private httpService: HttpService,  private authService: AuthService) { }

  submit(name: string) { 
    var subSection = new Sub_Section(name, this.authService.getCurrentUser().id!, this.sectionId)
    this.httpService.addSub_Section(subSection);
    this.httpService.getSub_Sections(this.sectionId);
  }

  ngOnInit(): void {
  }

}
