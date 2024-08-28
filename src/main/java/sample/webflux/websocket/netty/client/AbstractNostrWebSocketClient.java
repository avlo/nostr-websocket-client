package sample.webflux.websocket.netty.client;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public abstract class AbstractNostrWebSocketClient {
  private final String relayUrl;

  private final ConfigurableApplicationContext applicationContext;
  private final WebSocketHandler webSocketHandler;

  AbstractNostrWebSocketClient(ConfigurableApplicationContext applicationContext, String relayUrl) {
    this.applicationContext = applicationContext;
    this.relayUrl = relayUrl;
    this.webSocketHandler = new WebSocketHandler();
    this.webSocketHandler.connect(new ReactorNettyWebSocketClient(), getURI());
  }

  void sendRequestMessage(String json, String subscriptionId) {
    sendMessage(json);
    countdownClose();
  }

  public void sendMessage(String message) {
    Mono
        .fromRunnable(
            () -> webSocketHandler.send(message)
        )
        .thenMany(webSocketHandler.receive())
        .doOnNext(
            System.out::println
        )
        .subscribe();
  }

  private void countdownClose() {
    Mono
        .delay(Duration.ofSeconds(1))
        .publishOn(Schedulers.boundedElastic())
        .subscribe(value -> {
//          closeMethodNostr(subscriptionId);
          closeMethodForce();
        });
  }

  private void closeMethodForce() {
    webSocketHandler.disconnect();
    SpringApplication.exit(applicationContext, () -> 0);
  }

  private void closeMethodNostr(String subscriptionId) {
    sendMessage("[\"CLOSE\",\"" + subscriptionId + "\"]");
  }

  private URI getURI() {
    try {
      return new URI(relayUrl);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
