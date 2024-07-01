import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SectionComponent } from './section/section.component';
import { SubSectionComponent } from './sub-section/sub-section.component';
import { TopicComponent } from './topic/topic.component';
import { PostComponent } from './post/post.component';
import { SectionListComponent } from './section/section-list/section-list.component';
import { SectionItemComponent } from './section/section-list/section-item/section-item.component';
import { HttpClientModule } from '@angular/common/http';
import { HttpService } from './shared/http.service'
import { TopicService } from './topic/topic.service'
import { PostService } from './post/post.service'
import { SectionService } from './section/section.service'
import { SubSectionService } from './sub-section/sub-section.service';
import { HeaderComponent } from './header/header.component';
import { SubSectionListComponent } from './sub-section/sub-section-list/sub-section-list.component';
import { SubSectionItemComponent } from './sub-section/sub-section-list/sub-section-item/sub-section-item.component';
import { TopicListComponent } from './topic/topic-list/topic-list.component';
import { TopicItemComponent } from './topic/topic-list/topic-item/topic-item.component';
import { PostListComponent } from './post/post-list/post-list.component';
import { PostItemComponent } from './post/post-list/post-item/post-item.component';
import { AuthComponent } from './auth/auth.component';
import { TopicCreateComponent } from './topic/topic-list/topic-create/topic-create.component';
import { PostCreateComponent } from './post/post-list/post-create/post-create.component';
import { FormsModule } from '@angular/forms';
import { AuthService } from './auth/auth.service';
import { FacebookLoginProvider, GoogleLoginProvider, SocialAuthServiceConfig, SocialLoginModule } from '@abacritt/angularx-social-login';
import { ProfileComponent } from './profile/profile.component';
import { ConversationComponent } from './conversation/conversation.component';
import { MessageComponent } from './message/message.component';
import { ConversationListComponent } from './conversation/conversation-list/conversation-list.component';
import { ConversationItemComponent } from './conversation/conversation-list/conversation-item/conversation-item.component';
import { MessageListComponent } from './message/message-list/message-list.component';
import { MessageItemComponent } from './message/message-list/message-item/message-item.component';
import { RuleComponent } from './rule/rule.component';
import { RuleListComponent } from './rule/rule-list/rule-list.component';
import { RuleItemComponent } from './rule/rule-list/rule-item/rule-item.component';
import { SearchComponent } from './search/search.component';
import { SearchTopicListComponent } from './search/search-topic-list/search-topic-list.component';
import { SearchUserListComponent } from './search/search-user-list/search-user-list.component';
import { SearchTopicItemComponent } from './search/search-topic-list/search-topic-item/search-topic-item.component';
import { SearchUserItemComponent } from './search/search-user-list/search-user-item/search-user-item.component';
import { ProfileActivityListComponent } from './profile/profile-activity-list/profile-activity-list.component';
import { ProfileActivityItemComponent } from './profile/profile-activity-list/profile-activity-item/profile-activity-item.component';
import { ConversationService } from './conversation/conversation.service';
import { MessageService } from './message/message.service';
import { RuleService } from './rule/rule.service';
import { UserService } from './profile/user.service';
import { SearchService } from './search/search.service';
import { SubSectionCreateComponent } from './sub-section/sub-section-list/sub-section-create/sub-section-create.component';
import { SectionCreateComponent } from './section/section-list/section-create/section-create.component';
import { SectionEditComponent } from './section/section-list/section-item/section-edit/section-edit.component';
import { SubSectionEditComponent } from './sub-section/sub-section-list/sub-section-item/sub-section-edit/sub-section-edit.component';
import { TopicEditComponent } from './topic/topic-list/topic-item/topic-edit/topic-edit.component';
import { PostEditComponent } from './post/post-list/post-item/post-edit/post-edit.component';
import { ProfileChangePassComponent } from './profile/profile-change-pass/profile-change-pass.component';
import { MessageCreateComponent } from './message/message-list/message-create/message-create.component';
import { RuleCreateComponent } from './rule/rule-list/rule-create/rule-create.component';
import { RuleEditComponent } from './rule/rule-list/rule-item/rule-edit/rule-edit.component';
import { ConfigService } from './shared/config.service';
import { Config } from './shared/config.model';
import { map } from 'rxjs';

const init = (configService: ConfigService) => {
  return () => {
    return configService.readConfig();
  };
};

const getConfig = (configService: ConfigService) => { 
  return configService.readConfig().then((config => {
    let providers : any[] = [];
    providers.push({ id: FacebookLoginProvider.PROVIDER_ID,
      provider: new FacebookLoginProvider(
        config.fbId
      )},
      { id: GoogleLoginProvider.PROVIDER_ID,
        provider: new GoogleLoginProvider(
          config.googleId
        )})

      return {
        autologin: false,
        providers: providers,
      } as SocialAuthServiceConfig;
  }));
    
  
}


@NgModule({
  declarations: [
    AppComponent,
    SectionComponent,
    SubSectionComponent,
    TopicComponent,
    PostComponent,
    SectionListComponent,
    SectionItemComponent,
    HeaderComponent,
    SubSectionListComponent,
    SubSectionItemComponent,
    TopicListComponent,
    TopicItemComponent,
    PostListComponent,
    PostItemComponent,
    AuthComponent,
    TopicCreateComponent,
    PostCreateComponent,
    ProfileComponent,
    ConversationComponent,
    MessageComponent,
    ConversationListComponent,
    ConversationItemComponent,
    MessageListComponent,
    MessageItemComponent,
    RuleComponent,
    RuleListComponent,
    RuleItemComponent,
    SearchComponent,
    SearchTopicListComponent,
    SearchUserListComponent,
    SearchTopicItemComponent,
    SearchUserItemComponent,
    ProfileActivityListComponent,
    ProfileActivityItemComponent,
    SubSectionCreateComponent,
    SectionCreateComponent,
    SectionEditComponent,
    SubSectionEditComponent,
    TopicEditComponent,
    PostEditComponent,
    ProfileChangePassComponent,
    MessageCreateComponent,
    RuleCreateComponent,
    RuleEditComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    SocialLoginModule
  ],
  providers: [
    HttpService,
    TopicService,
    PostService,
    SectionService,
    SubSectionService,
    AuthService,
    ConversationService,
    MessageService,
    RuleService,
    UserService,
    SearchService,
    ConfigService,
    {
      provide: APP_INITIALIZER,
      useFactory: init,
      multi: true,
      deps: [ConfigService]
    },

   /* {
      provide: 'SocialAuthServiceConfig',
      //useFactory: socialAuthServiceConfigFactory,
      useValue: new Promise(async resolve => {
        const config = await init as unknown as Config ;
        console.log(config)
        console.log(init)
        resolve({
          autoLogin: false,
          providers: [
            {
              id: FacebookLoginProvider.PROVIDER_ID,
              provider: new FacebookLoginProvider(
                config.fbId
              )
            },
            {
              id: GoogleLoginProvider.PROVIDER_ID,
              provider: new GoogleLoginProvider(config.googleId),
            }
          ]
        } as SocialAuthServiceConfig);
      })
    },

    */{
      provide: 'SocialAuthServiceConfig',
      useFactory: getConfig,
      deps: [ConfigService],
      
    }
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(private configService: ConfigService) { }
}
