import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {engineTypes} from '../interfaces/engine-types-const';
import {MatRadioModule} from '@angular/material/radio';
import {configDictionary} from '../interfaces/config-dict';
import { MatButton} from '@angular/material/button';
import {ConnectionService} from '../services/connection.service';
import {ConfigDto} from '../interfaces/config-dto';
import {interval, Observable, Subscription} from 'rxjs';

@Component({
  selector: 'app-form-component',
  templateUrl: './form-component.component.html',
  styleUrls: ['./form-component.component.css']
})
export class FormComponentComponent implements OnInit {

  engineTypes = engineTypes;
  selectedEngine = '';
  private analysisRunning = false;
  states = {
    state1: null,
    state2: null
  };
  private stateSub: Subscription;

  optionalComponents = {
    oilReplSystem: false,
    divValveDuplFilter: false,
    duplFuelFilter: false,
    divValveFuelFilter: false,
    fuelLeakageMonitor: false
  };
  optionalComponentsKeys = Object.keys(this.optionalComponents);

  results = {};

  constructor(private cs: ConnectionService) { }

  ngOnInit(): void {
    this.cs.http_getStates().subscribe(response => {
      this.states = response;
    });
  }

  getPropertyTranslation(key: string): string {
    return configDictionary[key];
  }

  analyse(): void {
    this.analysisRunning = true;
    this.results = {}; // reset
    let dto: ConfigDto;
    dto = new ConfigDto(
      null,
      null,
      this.selectedEngine,
      this.optionalComponents.oilReplSystem,
      this.optionalComponents.divValveDuplFilter,
      this.optionalComponents.duplFuelFilter,
      this.optionalComponents.divValveFuelFilter,
      this.optionalComponents.fuelLeakageMonitor,
      null,
      null
    );

    // WebSocket wäre besser, aber funktioniert nicht bisher
    this.cs.http_submit(dto).subscribe(response => {
      this.results['Analysis1'] = response.successful1;
      this.results['Analysis2'] = response.successful2;
      this.analysisRunning = false;
      this.stateSub.unsubscribe();
      this.cs.http_getStates().subscribe(states => {
        this.states = states;
      });
      this.cs.emitDataChangedEvent();
    });

    this.stateSub = interval(2000).subscribe(val => {
      console.log('Requesting states...');
      this.cs.http_getStates().subscribe(response => {
        this.states = response;
      });
    });
  }

  analyseButtonDisabled(): boolean {
    return (this.selectedEngine.length === 0) || this.analysisRunning;
  }
}
