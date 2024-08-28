package sample.webflux.websocket.netty.client;

import reactor.core.publisher.Mono;

public class ClientLogic {

  public void doLogic(Client client, String message) {
    Mono
        .fromRunnable(
            () -> client.send(message)
        )
        .thenMany(client.receive())
        .doOnNext(
            System.out::println
        )
        .subscribe();
  }
}
