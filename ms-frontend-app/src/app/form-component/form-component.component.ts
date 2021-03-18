import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {engineTypes} from '../interfaces/engine-types-const';
import {MatRadioModule} from '@angular/material/radio';
import {configDictionary} from "../interfaces/config-dict";
import { MatButton} from '@angular/material/button';
import {ConnectionService} from "../services/connection.service";
import {ConfigDto} from "../interfaces/config-dto";

@Component({
  selector: 'app-form-component',
  templateUrl: './form-component.component.html',
  styleUrls: ['./form-component.component.css']
})
export class FormComponentComponent implements OnInit {

  engineTypes = engineTypes;
  selectedEngine = '';

  optionalComponents = {
    oilReplSystem: false,
    divValveDuplFilter: false,
    duplFuelFilter: false,
    divValveFuelFilter: false,
    fuelLeakageMonitor: false
  };
  optionalComponentsKeys = Object.keys(this.optionalComponents);

  dataToSubmit = {};

  constructor(private cs: ConnectionService) { }

  ngOnInit(): void {
  }

  getPropertyTranslation(key: string): string {
    return configDictionary[key];
  }

  analyze(): void {
    Object.keys(configDictionary).forEach(key => {
      if (this.optionalComponentsKeys.includes(key)) {
        this.dataToSubmit[key] = this.optionalComponents[key];
      } else if (key === 'engineType') {
        this.dataToSubmit[key] = this.selectedEngine;
      } else {
        this.dataToSubmit[key] = null;
      }
    });
  }
}
