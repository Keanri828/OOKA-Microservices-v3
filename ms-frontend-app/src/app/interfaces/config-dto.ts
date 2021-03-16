export interface ConfigDto {
  id: string;
  timestamp: Date;
  EngineType: string; // values of engine-types-const
  oilReplSystem: boolean;
  divValveDuplFilter: boolean;
  duplFuelFilter: boolean;
  divValveFuelFilter: boolean;
  fuelLeakageMonitor: boolean;
}
