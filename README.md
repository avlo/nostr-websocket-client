# Spring Boot 3.3.1 WebFlux Reactive WebSocket Nostr client  #

----

### Dependencies ###
#### 1.  A separately running Nostr relay. ####  
_See [SuperConductor](https://github.com/avlo/superconductor) for a Nostr-Relay reference implementation_
#### 2. Update [application.yml / relayUrl](src/main/resources/application.yml#L13) configuration. ####  

----

### Running the Client ###
    $ ./gradlew bootRun

A single client instance will connect to the relay  specified in [application.yml](src/main/resources/application.yml#L13).  

It will then send a message while logging any messages received from the relay. 

After 5 seconds the client will disconnect, and the application will terminate.
