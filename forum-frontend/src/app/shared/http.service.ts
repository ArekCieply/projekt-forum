import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { PostService } from '../post/post.service';
import { SectionService } from '../section/section.service';
import { SubSectionService } from '../sub-section/sub-section.service';
import { TopicService } from '../topic/topic.service';
import { Section } from './section.model';
import { exhaustMap, map, Subject, take, tap } from "rxjs";
import { Sub_Section } from './sub-section.model';
import { Post } from './post.model';
import { Topic } from './topic.model';
import { AuthService } from '../auth/auth.service';
import { Conversation } from './conversation.model';
import { ConversationService } from '../conversation/conversation.service';
import { MessageService } from '../message/message.service';
import { Message } from './message.model';
import { Rule } from './rule.model';
import { RuleService } from '../rule/rule.service';
import { User } from './user.model';
import { UserService } from '../profile/user.service';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  authData!: string[];
  convId = new Subject<Conversation>();
  private conv!: Conversation;
  //url: string;
  constructor(
    private http: HttpClient,
    private postService: PostService,
    private sectionService: SectionService,
    private sub_sectionService: SubSectionService,
    private topicService: TopicService,
    private authService: AuthService,
    private conversationService: ConversationService,
    private messageService: MessageService,
    private ruleService: RuleService,
    private userService: UserService,
    private configService: ConfigService,

  ) {
  }


  httpOptions = {
    headers: new HttpHeaders
  };

  httpOptionsParams = {
    params: new HttpParams,
    headers: new HttpHeaders
  };


  getHeaders() {
    console.log(this.authService.getToken())
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(this.authService.getCurrentUser().name + ':' + this.authService.getToken())
    })
  }
getUrl(){
  return this.configService.getUrl();
}

  addSection(section: Section) {
    this.httpOptions.headers = this.getHeaders()
    this.http.post(this.getUrl()+'section', section, this.httpOptions).subscribe((response) => {
      console.log(response);
      this.getSections()
    });
  }
  getSections() {
    this.http.get<Section[]>(this.getUrl()+'section').pipe(
      tap((sections) => {
        this.sectionService.setSections(sections);
      })
    )
      .subscribe((response) => {
        console.log(response);
      });
  }
  deleteSection(section: Section) {
    this.httpOptionsParams.headers = this.getHeaders()
    const params = new HttpParams().append('sectionId', section.id!);
    this.httpOptionsParams.params = params
    this.http.delete(this.getUrl()+'section', this.httpOptionsParams).subscribe((response) => {
      console.log(response);
      this.getSections()
    });
  }
  editSection(section: Section) {
    this.httpOptionsParams.headers = this.getHeaders()
    const params = new HttpParams().append('sectionId', section.id!);
    this.httpOptionsParams.params = params
    this.http.patch(this.getUrl()+'section', section, this.httpOptionsParams).subscribe((response) => {
      console.log(response);
      this.getSections()
    });
  }

  addSub_Section(sub_section: Sub_Section) {
    this.httpOptions.headers = this.getHeaders()
    this.http.post(this.getUrl()+'sub_section', sub_section, this.httpOptions).subscribe((response) => {
      console.log(response);
      this.getSub_Sections(sub_section.sectionId)
    });
  }
  getSub_Sections(section_id: number) {
    const params = new HttpParams().append('section_id', section_id);
    this.http.get<Sub_Section[]>(this.getUrl()+'sub_section', { params }).pipe(
      tap((sub_sections) => {
        this.sub_sectionService.setSub_Sections(sub_sections);
      })
    )
      .subscribe((response) => {
        console.log(response);
      });
  }
  deleteSub_Section(sub_section: Sub_Section) {
    this.httpOptionsParams.headers = this.getHeaders()
    const params = new HttpParams().append('subSectionId', sub_section.id!);
    this.httpOptionsParams.params = params
    this.http.delete(this.getUrl()+'sub_section', this.httpOptionsParams).subscribe((response) => {
      console.log(response);
      this.getSub_Sections(sub_section.sectionId)
    });
  }
  editSub_Section(sub_section: Sub_Section) {
    this.httpOptionsParams.headers = this.getHeaders()
    const params = new HttpParams().append('subSectionId', sub_section.id!);
    this.httpOptionsParams.params = params
    this.http.patch(this.getUrl()+'sub_section', sub_section, this.httpOptionsParams).subscribe((response) => {
      console.log(response);
      this.getSub_Sections(sub_section.sectionId)
    });
  }

  addPost(post: Post) {
    this.httpOptions.headers = this.getHeaders()
    this.http.post(this.getUrl()+'post', post, this.httpOptions).subscribe((response) => {
      console.log(response);
      this.getPosts(post.topicId)
    });
  }
  getPosts(topic_id: number) {
    const params = new HttpParams().append('topic_id', topic_id);
    this.http.get<Post[]>(this.getUrl()+'post', { params }).pipe(
      tap((posts) => {
        this.postService.setPosts(posts);
      })
    )
      .subscribe((response) => {
        console.log(response);
      });
  }
  deletePost(post: Post) {
    this.httpOptionsParams.headers = this.getHeaders()
    const params = new HttpParams().append('postId', post.id!);
    this.httpOptionsParams.params = params//check
    this.http.delete(this.getUrl()+'post',  this.httpOptionsParams).subscribe((response) => {
      console.log(response);
      this.getPosts(post.topicId)
    });
  }
  editPost(post: Post) {
    this.httpOptionsParams.headers = this.getHeaders()
    const params = new HttpParams().append('postId', post.id!);
    this.httpOptionsParams.params = params
    this.http.patch(this.getUrl()+'post', post, this.httpOptionsParams).subscribe((response) => {
      console.log(response);
      this.getPosts(post.topicId)
    });
  }
  plusPost(targetId: number, userId: number, topicId: number) {
    this.httpOptions.headers = this.getHeaders()
    this.http.post(this.getUrl()+'post/vote/plus', { targetId, userId }, this.httpOptions).subscribe((response) => {
      console.log(response);
      this.getPosts(topicId);
    });
  }
  minusPost(targetId: number, userId: number, topicId: number) {
    this.httpOptions.headers = this.getHeaders()
    this.http.post(this.getUrl()+'post/vote/minus', { targetId, userId }, this.httpOptions).subscribe((response) => {
      console.log(response);
      this.getPosts(topicId);
    });
  }

  addTopic(topic: Topic) {
    this.httpOptions.headers = this.getHeaders()
    this.http.post(this.getUrl()+'topic', topic, this.httpOptions).subscribe((response) => {
      console.log(response);
      this.getTopics(topic.subSectionId)
    });
  }
  getTopics(sub_section_id: number) {
    const params = new HttpParams().append('sub_section_id', sub_section_id);
    this.http.get<Topic[]>(this.getUrl()+'topic', { params }).pipe(
      tap((topics) => {
        this.topicService.setTopics(topics);
      })
    )
      .subscribe((response) => {
        console.log(response);
      });
  }
  deleteTopic(topic: Topic) {
    this.httpOptionsParams.headers = this.getHeaders()
    const params = new HttpParams().append('topicId', topic.id!);
    this.httpOptionsParams.params = params
    this.http.delete(this.getUrl()+'delete', this.httpOptionsParams).subscribe((response) => {
      console.log(response);
      this.getTopics(topic.subSectionId)
    });
  }
  editTopic(topic: Topic) {
    this.httpOptionsParams.headers = this.getHeaders()
    const params = new HttpParams().append('topicId', topic.id!);
    this.httpOptionsParams.params = params
    this.http.patch(this.getUrl()+'edit', topic, this.httpOptionsParams).subscribe((response) => {
      console.log(response);
      this.getTopics(topic.subSectionId)
    });
  }
  plusTopic(targetId: number, userId: number) {
    this.httpOptions.headers = this.getHeaders()
    this.http.post(this.getUrl()+'topic/vote/plus', { targetId, userId }, this.httpOptions).subscribe((response) => {
      console.log(response);
    });
  }
  minusTopic(targetId: number, userId: number) {
    this.httpOptions.headers = this.getHeaders()
    this.http.post(this.getUrl()+'topic/vote/minus', { targetId, userId }, this.httpOptions).subscribe((response) => {
      console.log(response);
    });
  }

  addConversation(conversation: Conversation) {
    this.httpOptions.headers = this.getHeaders()
    this.http.post(this.getUrl()+'private_conversation', conversation, this.httpOptions).subscribe((response) => {
      console.log(response);
      /// this.convId = <Conversation>response;

      this.conv = <Conversation>response
      this.convId.next(this.conv);
      console.log(this.conv)
    });
  }

  getConversations(userId: number) {
    const params = new HttpParams().append('userId', userId);
    this.httpOptionsParams.headers = this.getHeaders()
    this.httpOptionsParams.params = params
    this.http.get<Conversation[]>(this.getUrl()+'private_conversation', this.httpOptionsParams).pipe(
      tap((conversations) => {
        this.conversationService.setConversations(conversations);
      })
    )
      .subscribe((response) => {
        console.log(response);
      });
  }

  addMessage(message: Message) {
    this.httpOptions.headers = this.getHeaders()
    this.http.post(this.getUrl()+'private_message', message, this.httpOptions).subscribe((response) => {
      console.log(response);
      this.getMessages(message.privateConversationId)
    });
  }
  getMessages(conversationId: number) {
    const params = new HttpParams().append('conversationId', conversationId);
    this.http.get<Message[]>(this.getUrl()+'private_message', { params }).pipe(
      tap((messages) => {
        this.messageService.setMessages(messages);
      })
    )
      .subscribe((response) => {
        console.log(response);
      });
  }

  addRule(rule: Rule) {
    this.httpOptions.headers = this.getHeaders()
    this.http.post(this.getUrl()+'rule', rule, this.httpOptions).subscribe((response) => {
      console.log(response);
      this.getRules()
    });
  }
  getRules() {
    this.http.get<Rule[]>(this.getUrl()+'rule').pipe(
      tap((rules) => {
        this.ruleService.setRules(rules);
      })
    )
      .subscribe((response) => {
        console.log(response);
      });
  }
  deleteRule(rule: Rule) {
    this.httpOptionsParams.headers = this.getHeaders()
    const params = new HttpParams().append('ruleId', rule.id!);
    this.httpOptionsParams.params = params
    this.http.delete(this.getUrl()+'rule/delete', this.httpOptionsParams).subscribe((response) => {
      console.log(response);
      this.getRules()
    });
  }
  editRule(rule: Rule) {
    this.httpOptionsParams.headers = this.getHeaders()
    const params = new HttpParams().append('ruleId', rule.id!);
    this.httpOptionsParams.params = params
    this.http.patch(this.getUrl()+'rule/edit', rule, this.httpOptionsParams).subscribe((response) => {
      console.log(response);
      this.getRules()
    });
  }

  getUser(id: number) {
    const params = new HttpParams().append('id', id);
    this.http.get<User>(this.getUrl()+'users', { params }).pipe(
      tap((user) => {
        this.userService.setUser(user);
      })
    )
      .subscribe((response) => {
        console.log(response);
      });
  }

  getUserActivity(userId: number) {
    const params = new HttpParams().append('userId', userId);
    this.http.get<Topic[]>(this.getUrl()+'topic/user_activity', { params }).pipe(
      tap((topic) => {
        this.topicService.setTopics(topic);
      })
    )
      .subscribe((response) => {
        console.log(response);
      });
  }

  ban(userId: number, targetId: number) {
    this.httpOptions.headers = this.getHeaders()
    this.http.post(this.getUrl()+'users/ban', { userId, targetId }, this.httpOptions).subscribe((response) => {
      console.log(response);
      this.getUser(targetId);
    },
      err => {
        console.log(err);
      }
    );
  }

  unBan(userId: number, targetId: number) {
    this.httpOptions.headers = this.getHeaders()
    this.http.post(this.getUrl()+'users/unban', { userId, targetId }, this.httpOptions).subscribe((response) => {
      console.log(response);
      this.getUser(targetId);
    },
      err => {
        console.log(err);
      }
    );
  }

  promote(userId: number, targetId: number) {
    this.httpOptions.headers = this.getHeaders()
    this.http.post(this.getUrl()+'users/promote', { userId, targetId }, this.httpOptions).subscribe((response) => {
      console.log(response);
      this.getUser(targetId);
    },
      err => {
        console.log(err);
      }
    );
  }

  demote(userId: number, targetId: number) {
    this.httpOptions.headers = this.getHeaders()
    this.http.post(this.getUrl()+'users/demote', { userId, targetId }, this.httpOptions).subscribe((response) => {
      console.log(response);
      this.getUser(targetId);
    },
      err => {
        console.log(err);
      }
    );
  }

  getConvId() {
    return this.conv;
  }
}
