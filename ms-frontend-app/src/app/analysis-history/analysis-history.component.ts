import { Component, OnInit } from '@angular/core';
import {ConnectionService} from '../services/connection.service';

@Component({
  selector: 'app-analysis-history',
  templateUrl: './analysis-history.component.html',
  styleUrls: ['./analysis-history.component.css']
})
export class AnalysisHistoryComponent implements OnInit {

  // private configurationHistory: ConfigDto[];
  constructor(private cs: ConnectionService) { }

  ngOnInit(): void {
  }



}
