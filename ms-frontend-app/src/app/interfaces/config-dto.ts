export class ConfigDto {
  id: string;
  timestamp: Date;
  engineType: string; // values of engine-types-const
  oilReplSystem: boolean;
  divValveDuplFilter: boolean;
  duplFuelFilter: boolean;
  divValveFuelFilter: boolean;
  fuelLeakageMonitor: boolean;
  successful1: boolean;
  successful2: boolean;

  constructor(id: string,
              timestamp: Date,
              engineType: string,
              oilReplSystem: boolean,
              divValveDuplFilter: boolean,
              duplFuelFilter: boolean,
              divValveFuelFilter: boolean,
              fuelLeakageMonitor: boolean,
              successful1: boolean,
              successful2: boolean) {
    this.id = id;
    this.timestamp = timestamp;
    this.engineType = engineType;
    this.oilReplSystem = oilReplSystem;
    this.divValveDuplFilter = divValveDuplFilter;
    this.duplFuelFilter = duplFuelFilter;
    this.divValveFuelFilter = divValveFuelFilter;
    this.fuelLeakageMonitor = fuelLeakageMonitor;
    this.successful1 = successful1;
    this.successful2 = successful2;
  }
}
