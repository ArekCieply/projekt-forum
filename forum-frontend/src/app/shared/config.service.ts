import { Injectable } from '@angular/core';
import { Config } from './config.model';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom, lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private config!: Config;
  constructor(private http: HttpClient) { }

   readConfig(): Promise<Config> {
    return lastValueFrom(this.http
      .get<Config>('./config.json'))
      .then(config => this.config = config!);
  }

  getConfig() {
    return this.http
    .get<Config>('./config.json')
  }
  getUrl(){
    return this.config.url
  }
}
