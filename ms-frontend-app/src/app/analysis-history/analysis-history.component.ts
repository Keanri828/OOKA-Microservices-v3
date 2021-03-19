import {Component, OnInit, ViewChild, AfterViewInit} from '@angular/core';
import { ConfigDto } from '../interfaces/config-dto';
import {ConnectionService} from '../services/connection.service';
import { MatTableModule } from '@angular/material/table';
import {configDictionary} from '../interfaces/config-dict';
import {MatButtonModule} from '@angular/material/button';

@Component({
  selector: 'app-analysis-history',
  templateUrl: './analysis-history.component.html',
  styleUrls: ['./analysis-history.component.css']
})
export class AnalysisHistoryComponent implements OnInit {

  configurationHistory: ConfigDto[];
  configKeys: string[] = [];

  constructor(private cs: ConnectionService) {
    Object.keys(configDictionary).forEach(key => {
      this.configKeys.push(key);
    });
  }

  ngOnInit(): void {
    this.cs.http_getAll().subscribe(configs => {
      this.configurationHistory = configs;
      // console.log(configs);
      this.configurationHistory.sort((a, b) => {
        return new Date(b.timestamp).getTime() - new Date(a.timestamp).getTime();
      });
    });
  }

  loadConfig(id: string): void {
    console.log('Lade Konfiguration mit der ID ' + id);
    // todo
  }

  getPropertyTranslation(key: string): string {
    return configDictionary[key];
  }

  reformatDate(timestamp: Date): string {
    const d = new Date(timestamp);
    return d.getDate() + '.' + (d.getMonth() + 1)  + '.' + d.getFullYear() + ' '
      + d.getHours() + ':' + d.getMinutes();
  }

  // to change background color of insuccessful tasks
  isSuccessful(element: ConfigDto): boolean {
    return (element.successful1 && element.successful2);
  }


}
