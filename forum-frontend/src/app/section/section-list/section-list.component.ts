import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Section } from '../../shared/section.model';
import { SectionService } from '../section.service';

@Component({
  selector: 'app-section-list',
  templateUrl: './section-list.component.html',
  styleUrls: ['./section-list.component.css']
})
export class SectionListComponent implements OnInit {
  sections: Section[];
  subscription!: Subscription;
  constructor(private sectionService: SectionService) {this.sections=[] }

  ngOnInit(): void {
    this.sections=this.sectionService.getSections();
    this.subscription=this.sectionService.sectionsChanged.subscribe(
      (sections: Section[]) =>{
        this.sections=sections
      }
    )
  }

}
