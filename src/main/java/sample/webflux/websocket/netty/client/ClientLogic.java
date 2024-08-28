package sample.webflux.websocket.netty.client;

import reactor.core.publisher.Mono;

public class ClientLogic {

  public void doLogic(WebSocketService webSocketService, String message) {
    Mono
        .fromRunnable(
            () -> webSocketService.send(message)
        )
        .thenMany(webSocketService.receive())
        .doOnNext(
            System.out::println
        )
        .subscribe();
  }
}
