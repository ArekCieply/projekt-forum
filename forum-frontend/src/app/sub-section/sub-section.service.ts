import { Subject } from "rxjs";
import { Sub_Section } from "../shared/sub-section.model";

export class SubSectionService {
  private sub_sections: Sub_Section[] = [];
  subSectionsChanged = new Subject<Sub_Section[]>();
  getSub_Sections() {
    return this.sub_sections;
  }
  getSub_Section(index: number) {
    return this.sub_sections[index];
  }
  setSub_Sections(Sub_Section: Sub_Section[]) {
    this.sub_sections = Sub_Section;
    this.subSectionsChanged.next(this.sub_sections.slice());
  }
}
