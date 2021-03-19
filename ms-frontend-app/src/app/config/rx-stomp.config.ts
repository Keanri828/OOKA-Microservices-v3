import { InjectableRxStompConfig } from '@stomp/ng2-stompjs';

export const myRxStompConfig: InjectableRxStompConfig = {
  // server url
  brokerURL: 'ws://localhost:8080',

  // Headers, typical keys: login, passcode, host
  connectHeaders: {
    login: 'guest',
    passcode: 'guest'
  },

  // heartbeat intervals, 0 = disabled
  heartbeatIncoming: 0, // typically disabled
  heartbeatOutgoing: 20000, // typical: every 20 seconds

  // reconnecting attempt
  // typical: 500ms
  reconnectDelay: 0,

  // logs (not recommended in production)
  debug: (msg: string): void => {
    console.log(new Date(), msg);
  }
};
