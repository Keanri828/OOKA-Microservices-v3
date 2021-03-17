import { Component, OnInit } from '@angular/core';
import { ConfigDto } from '../interfaces/config-dto';
import {ConnectionService} from '../services/connection.service';
import { MatTableModule } from '@angular/material/table';
import {optionalPartsDictionary} from '../interfaces/optional-parts';
import {MatButtonModule} from '@angular/material/button';

@Component({
  selector: 'app-analysis-history',
  templateUrl: './analysis-history.component.html',
  styleUrls: ['./analysis-history.component.css']
})
export class AnalysisHistoryComponent implements OnInit {

  configurationHistory: ConfigDto[];
  configKeys: string[] = [
    'timestamp'
  ];

  constructor(private cs: ConnectionService) {
    Object.keys(optionalPartsDictionary).forEach(key => {
      this.configKeys.push(key);
    });
    this.configKeys.push('id');
  }

  ngOnInit(): void {
    this.cs.http_getAll().subscribe(configs => {
      this.configurationHistory = configs;
      // console.log(configs);
    });
  }



}
