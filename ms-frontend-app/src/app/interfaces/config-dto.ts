export interface ConfigDto {
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
}
