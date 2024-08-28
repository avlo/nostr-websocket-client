package sample.webflux.websocket.netty.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@Component
public class EventNostrWebSocketClient extends AbstractNostrWebSocketClient implements ApplicationListener<ApplicationReadyEvent> {
  String eventJson;

  //  @Autowired
  public EventNostrWebSocketClient(ConfigurableApplicationContext applicationContext, @Value("${relayUrl}") String relayUrl) {
    super(applicationContext, relayUrl);
    try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/text_event_json_input.txt"))) {
      this.eventJson = lines.collect(Collectors.joining("\n"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    sendRequestMessage(eventJson, "EventClientComponent-999");
  }
}
