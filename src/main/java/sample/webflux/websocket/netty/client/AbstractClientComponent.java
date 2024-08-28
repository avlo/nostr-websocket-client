package sample.webflux.websocket.netty.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public abstract class AbstractClientComponent {
  @Value("${relayUrl}")
  String relayUrl;

  private final ConfigurableApplicationContext applicationContext;
  private final WebSocketClient eventWebSocketClient;
  private final WebSocketService webSocketService;

  AbstractClientComponent(ConfigurableApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
    this.eventWebSocketClient = new ReactorNettyWebSocketClient();
    this.webSocketService = new WebSocketService();
  }

  void doStuff(String json, String subscriptionId) {
    webSocketService.connect(eventWebSocketClient, getURI());
    new ClientLogic().doLogic(webSocketService, json);

    Mono
        .delay(Duration.ofSeconds(1))
        .publishOn(Schedulers.boundedElastic())
        .subscribe(value -> {
//          closeMethodNostr(subscriptionId);
          closeMethodForce();
        });
  }

  private void closeMethodForce() {
    webSocketService.disconnect();
    SpringApplication.exit(applicationContext, () -> 0);
  }

  private void closeMethodNostr(String subscriptionId) {
    new ClientLogic().doLogic(webSocketService, "[\"CLOSE\",\"" + subscriptionId + "\"]");
  }

  private URI getURI() {
    try {
      return new URI(relayUrl);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
