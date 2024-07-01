import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AuthService } from '../../../../auth/auth.service';
import { HttpService } from '../../../../shared/http.service';
import { Sub_Section } from '../../../../shared/sub-section.model';

@Component({
  selector: 'app-sub-section-edit',
  templateUrl: './sub-section-edit.component.html',
  styleUrls: ['./sub-section-edit.component.css']
})
export class SubSectionEditComponent implements OnInit {
  @Input() sub_section!: Sub_Section;
  @Output() displayEvent = new EventEmitter<boolean>();
  constructor(private authService: AuthService, private httpService: HttpService) { }

  ngOnInit(): void {
  }

  save(name: string){
    this.sub_section.name=name;
    this.sub_section.userId=this.authService.getCurrentUser().id!;
    this.httpService.editSub_Section(this.sub_section);
    this.displayEvent.emit(false);
  }

}
