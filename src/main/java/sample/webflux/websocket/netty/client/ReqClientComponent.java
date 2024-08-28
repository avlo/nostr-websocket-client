package sample.webflux.websocket.netty.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ReqClientComponent extends AbstractClientComponent implements ApplicationListener<ApplicationReadyEvent> {

//  default id: 8f66a36101d3d152c6270e18f5622d1f8bce4ac5da9ab62d7c3cc0006e5914cc
//  default pubkey: bbbd79f81439ff794cf5ac5f7bff9121e257f399829e472c7a14d3e86fe76984

  private static final String REQ_CLIENT_COMPONENT_ID_001 = "ReqClientComponent-ID-001";
  private static final String idReqJson = "[\"REQ\",\"" + REQ_CLIENT_COMPONENT_ID_001 + "\",{\"ids\":[\"8f66a36101d3d152c6270e18f5622d1f8bce4ac5da9ab62d7c3cc0006e5914cc\"]}]";


  private static final String REQ_CLIENT_COMPONENT_AUTHOR_001 = "ReqClientComponent-AUTHOR-001";
  private static final String authorReqJson = "[\"REQ\",\"" + REQ_CLIENT_COMPONENT_AUTHOR_001 + "\",{\"authors\":[\"bbbd79f81439ff794cf5ac5f7bff9121e257f399829e472c7a14d3e86fe76984\"]}]";

  private static final String REQ_CLIENT_COMPONENT_ID_AUTHOR_001 = "ReqClientComponent-ID+AUTHOR-001";
  private static final String bothReqJson = "[\"REQ\",\"" + REQ_CLIENT_COMPONENT_ID_AUTHOR_001 + "\",{\"ids\":[\"5f66a36101d3d152c6270e18f5622d1f8bce4ac5da9ab62d7c3cc0006e5914cc\"],\"authors\":[\"bbbd79f81439ff794cf5ac5f7bff9121e257f399829e472c7a14d3e86fe76984\"]}]";

  private static final String REQ_CLIENT_COMPONENT_NONE_001 = "ReqClientComponent-NONE-001";
  private static final String none = "[\"REQ\",\"" + REQ_CLIENT_COMPONENT_NONE_001 + "\",{}]";

  @Autowired
  public ReqClientComponent(ConfigurableApplicationContext applicationContext) {
    super(applicationContext);
  }

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    doStuff(idReqJson, REQ_CLIENT_COMPONENT_ID_001);
//    doStuff(authorReqJson, REQ_CLIENT_COMPONENT_AUTHOR_001);
//    doStuff(bothReqJson, REQ_CLIENT_COMPONENT_ID_AUTHOR_001);
//    doStuff(none, REQ_CLIENT_COMPONENT_NONE_001);
  }
}
