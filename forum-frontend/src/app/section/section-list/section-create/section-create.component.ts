import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/auth.service';
import { HttpService } from '../../../shared/http.service';
import { Section } from '../../../shared/section.model';

@Component({
  selector: 'app-section-create',
  templateUrl: './section-create.component.html',
  styleUrls: ['./section-create.component.css']
})
export class SectionCreateComponent implements OnInit {

  constructor(private httpService: HttpService, private authService: AuthService) { }

  submit(content: string) {
    
    var section = new Section(content, this.authService.getCurrentUser().id!)
    this.httpService.addSection(section);
    //this.httpService.getSections();
  }

  ngOnInit(): void {
  }

}
