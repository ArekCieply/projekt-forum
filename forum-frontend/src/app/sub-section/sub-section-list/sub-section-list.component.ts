import { Component, Input, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Sub_Section } from '../../shared/sub-section.model';
import { SubSectionService } from '../sub-section.service';

@Component({
  selector: 'app-sub-section-list',
  templateUrl: './sub-section-list.component.html',
  styleUrls: ['./sub-section-list.component.css']
})
export class SubSectionListComponent implements OnInit {
  sub_sections! : Sub_Section[];
  @Input() sectionId!: number;
  subscription!: Subscription;
  constructor(private subSectionService: SubSectionService) { }

  ngOnInit(): void {
    this.sub_sections=this.subSectionService.getSub_Sections();
    this.subscription=this.subSectionService.subSectionsChanged.subscribe(
      (sub_sections: Sub_Section[]) =>{
        this.sub_sections=sub_sections
      }
    )
  }

}
