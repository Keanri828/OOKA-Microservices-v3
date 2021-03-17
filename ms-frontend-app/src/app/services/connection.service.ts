import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {config, Observable, throwError} from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import {ConfigDto} from '../interfaces/config-dto';

@Injectable({
  providedIn: 'root'
})
export class ConnectionService {

  private url = 'http://localhost:8080';
  private configs: ConfigDto[] = [];

  constructor(private http: HttpClient) { }

  private http_getAll(): void {
    // only for init and refresh
    const completeUrl = this.url + '/api/all';
    this.http.get<ConfigDto[]>(completeUrl, {responseType: 'json'}).subscribe(response => {
      response.forEach(item => {
        // console.log(item);
        this.configs.push(item);
      });
    });
    // console.log('(DEBUG) Received by http request:');
    // this.log_configs();
  }

  public getConfigs(): ConfigDto[] {
    if (this.configs.length === 0) {
      this.http_getAll();
    }
    return this.configs;
  }

  public refreshAndReturn(): ConfigDto[] {
    this.configs = [];
    return this.getConfigs();
  }

  private log_configs(): void {
    console.log(this.configs.toString());
  }
}
