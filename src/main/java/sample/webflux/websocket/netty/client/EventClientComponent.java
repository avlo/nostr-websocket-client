package sample.webflux.websocket.netty.client;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@Component
public class EventClientComponent extends AbstractClientComponent implements ApplicationListener<ApplicationReadyEvent> {
  String eventJson;

  //  @Autowired
  public EventClientComponent(ConfigurableApplicationContext applicationContext) {
    super(applicationContext);
    try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/text_event_json_input.txt"))) {
      this.eventJson = lines.collect(Collectors.joining("\n"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    doStuff(eventJson, "EventClientComponent-999");
  }
}
