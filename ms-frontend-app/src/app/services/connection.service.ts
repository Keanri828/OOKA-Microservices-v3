import {EventEmitter, Injectable, Output} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ConfigDto} from '../interfaces/config-dto';

@Injectable({
  providedIn: 'root'
})
export class ConnectionService {

  private url = 'http://localhost:8080';
  @Output() public dataChanged: EventEmitter<any> = new EventEmitter();

  constructor(private http: HttpClient) { }

  public http_getAll(): Observable<ConfigDto[]> {
    const completeUrl = this.url + '/api/all';
    return this.http.get<ConfigDto[]>(completeUrl, {responseType: 'json'});
  }

  public http_submit(dto: ConfigDto): Observable<ConfigDto> {
    const completeUrl = this.url + '/api/analyse';

    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    const options = { headers: headers};
    console.log(dto);
    // @ts-ignore
    return this.http.post(completeUrl, dto, options);
  }

  public http_getStates(): Observable<any> {
    const completeUrl = this.url + '/api/state';
    return this.http.get(completeUrl);
  }

  public emitDataChangedEvent(): void {
    this.dataChanged.emit(null);
  }
}
