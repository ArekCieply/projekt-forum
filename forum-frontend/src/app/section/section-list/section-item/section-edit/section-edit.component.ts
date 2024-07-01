import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AuthService } from '../../../../auth/auth.service';
import { HttpService } from '../../../../shared/http.service';
import { Section } from '../../../../shared/section.model';

@Component({
  selector: 'app-section-edit',
  templateUrl: './section-edit.component.html',
  styleUrls: ['./section-edit.component.css']
})
export class SectionEditComponent implements OnInit {
  @Input() section!: Section;
  @Output() displayEvent = new EventEmitter<boolean>();
  constructor(private authService: AuthService, private httpService: HttpService) { }

  ngOnInit(): void {
  }

  save(name: string){
    this.section.name=name;
    this.section.userId=this.authService.getCurrentUser().id!;
    this.httpService.editSection(this.section);
    this.displayEvent.emit(false);
  }
}
