import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthComponent } from './auth/auth.component';
import { ConversationComponent } from './conversation/conversation.component';
import { MessageComponent } from './message/message.component';
import { PostComponent } from './post/post.component';
import { ProfileComponent } from './profile/profile.component';
import { RuleComponent } from './rule/rule.component';
import { SearchComponent } from './search/search.component';
import { SectionComponent } from './section/section.component';
import { SubSectionComponent } from './sub-section/sub-section.component';
import { TopicComponent } from './topic/topic.component';

const routes: Routes = [
  {
    path: "section",
    component: SectionComponent,
    children: [
      //{path: "subsection/:id", component: SubSectionComponent,}
    ]
  },
  { path: "subsection/:id", component: SubSectionComponent, },
  { path: "topics/:id", component: TopicComponent, },
  { path: "topic/:id", component: PostComponent, },
  { path: "auth", component: AuthComponent, },
  { path: "user/:id", component: ProfileComponent, },
  {
    path: "converations/:id", component: ConversationComponent, children: [
      { path: "conversation/:id", component: MessageComponent, }
    ]
  },
  { path: "conversation/:id", component: MessageComponent, },
  { path: "search", component: SearchComponent, },
  { path: "rules", component: RuleComponent, }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
