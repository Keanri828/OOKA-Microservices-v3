import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {config, Observable, throwError} from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import {ConfigDto} from '../interfaces/config-dto';

@Injectable({
  providedIn: 'root'
})
export class ConnectionService {

  private url = 'http://localhost:18080';
  private configs: ConfigDto[] = [];

  constructor(private http: HttpClient) { }

  public http_getAll(): Observable<ConfigDto[]> {
    const completeUrl = this.url + '/api/all';
    return this.http.get<ConfigDto[]>(completeUrl, {responseType: 'json'});
  }

  http_submit(dto: ConfigDto): Observable<ConfigDto> {
    const completeUrl = this.url + '/api/analyse';

    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    const options = { headers: headers};
    console.log(dto);
    // @ts-ignore
    return this.http.post(completeUrl, dto, options);
  }
}
