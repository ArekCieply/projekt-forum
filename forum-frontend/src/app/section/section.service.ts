import { Subject } from "rxjs";
import { Section } from "../shared/section.model";

export class SectionService {
  private sections: Section[] = [];
  sectionsChanged = new Subject<Section[]>();
  getSections() {
    return this.sections;
  }
  getSection(index: number) {
    return this.sections[index];
  }
  setSections(Section: Section[]) {
    this.sections = Section;
    this.sectionsChanged.next(this.sections);//slice()
  }
  addSection(Section: Section){
    this.sections.push(Section);
  }
}