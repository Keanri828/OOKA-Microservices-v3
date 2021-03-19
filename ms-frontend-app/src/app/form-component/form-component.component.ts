import { Component, OnDestroy, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {engineTypes} from '../interfaces/engine-types-const';
import {MatRadioModule} from '@angular/material/radio';
import {configDictionary} from "../interfaces/config-dict";
import { MatButton} from '@angular/material/button';
import {ConnectionService} from "../services/connection.service";
import {ConfigDto} from "../interfaces/config-dto";
import { RxStompService } from '@stomp/ng2-stompjs';
import { Message } from '@stomp/stompjs'

@Component({
  selector: 'app-form-component',
  templateUrl: './form-component.component.html',
  styleUrls: ['./form-component.component.css']
})
export class FormComponentComponent implements OnInit, OnDestroy {

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

  results = {};

  messages: string[] = [];
  topicSubscription: any;

  constructor(private cs: ConnectionService, private rxStompService: RxStompService) { }

  ngOnDestroy(): void {
        this.topicSubscription.unsubscribe();
    }

  ngOnInit(): void {
    this.topicSubscription = this.rxStompService.watch('/socket/websocket').subscribe((message: Message) => {
      this.messages.push(message.body);
    });
  }

  getPropertyTranslation(key: string): string {
    return configDictionary[key];
  }

  analyse(): void {
    // generate dto
    /*Object.keys(configDictionary).forEach(key => {
      if (this.optionalComponentsKeys.includes(key)) {
        this.dataToSubmit[key] = this.optionalComponents[key];
      } else if (key === 'engineType') {
        this.dataToSubmit[key] = this.selectedEngine;
      } else {
        this.dataToSubmit[key] = null;
      }
    });*/

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

    // todo idealerweise WebSocket, um den Status verfolgen zu können
    this.cs.http_submit(dto).subscribe(response => {
      this.results['Analysis1'] = response.successful1;
      this.results['Analysis2'] = response.successful2;
    });
  }

  onSendMessage(): void {
    const message = `Message generated at ${new Date()}`;
    this.rxStompService.publish({ destination: '/api/sockettest', body: message});
  };
}
