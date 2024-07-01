import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SectionService } from '../section/section.service';
import { HttpService } from '../shared/http.service';

@Component({
  selector: 'app-sub-section',
  templateUrl: './sub-section.component.html',
  styleUrls: ['./sub-section.component.css']
})
export class SubSectionComponent implements OnInit {
  id!: number;

  constructor(private sectionService: SectionService, private route: ActivatedRoute, private httpService: HttpService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params=>{this.id=params['id']})
    this.httpService.getSub_Sections(this.id)
  }

}
