package sample.webflux.websocket.netty.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class NostrWebSocketClient extends AbstractNostrWebSocketClient implements ApplicationListener<ApplicationReadyEvent> {

//  default id: 8f66a36101d3d152c6270e18f5622d1f8bce4ac5da9ab62d7c3cc0006e5914cc
//  default pubkey: bbbd79f81439ff794cf5ac5f7bff9121e257f399829e472c7a14d3e86fe76984

  private static final String REQ_CLIENT_SUBSCRIBER_001 = "ReqClientComponent-ID-001";
  //  private static final String REQ_CLIENT_EVENT_ID_001 = "b308ee86a086ff9d7435197c8d0d92412d141653e79535e2805cf15617a699d6";
//  private static final String REQ_CLIENT_EVENT_ID_001 = "e69b40aa10f4ac9cc654f25f564b2ea6e6d73bc9a88848e80c0a82cfaf73f06a";
  private static final String REQ_CLIENT_EVENT_ID_001 = "299ab85049a7923e9cd82329c0fa489ca6fd6d21feeeac33543b1237e14a9e07";
  private static final String idReqJson = "[\"REQ\",\"" + REQ_CLIENT_SUBSCRIBER_001 + "\",{\"ids\":[\"" + REQ_CLIENT_EVENT_ID_001 + "\"]}]";

  private static final String REQ_CLIENT_SUBSCRIBER_002 = "ReqClientComponent-AUTHOR-002";
  private static final String REQ_CLIENT_AUTHOR_002 = "bbbd79f81439ff794cf5ac5f7bff9121e257f399829e472c7a14d3e86fe76984";
  private static final String authorReqJson = "[\"REQ\",\"" + REQ_CLIENT_SUBSCRIBER_002 + "\",{\"authors\":[\"" + REQ_CLIENT_AUTHOR_002 + "\"]}]";

  private static final String REQ_CLIENT_SUBSCRIBER_003 = "ReqClientComponent-ID+AUTHOR-003";
  private static final String REQ_CLIENT_EVENT_ID_003 = "b308ee86a086ff9d7435197c8d0d92412d141653e79535e2805cf15617a699d6";
  private static final String REQ_CLIENT_AUTHOR_003 = "bbbd79f81439ff794cf5ac5f7bff9121e257f399829e472c7a14d3e86fe76984";
  private static final String bothReqJson = "[\"REQ\",\"" + REQ_CLIENT_SUBSCRIBER_003 + "\",{\"ids\":[\"" + REQ_CLIENT_EVENT_ID_003 + "\"],\"authors\":[\"" + REQ_CLIENT_AUTHOR_003 + "\"]}]";

  private static final String REQ_CLIENT_SUBSCRIBER_004 = "ReqClientComponent-ID+AUTHOR-004";
  private static final String REQ_CLIENT_EVENT_ID_004_a = "b308ee86a086ff9d7435197c8d0d92412d141653e79535e2805cf15617a699d6";
  private static final String REQ_CLIENT_EVENT_ID_004_b = "e69b40aa10f4ac9cc654f25f564b2ea6e6d73bc9a88848e80c0a82cfaf73f06a";
  private static final String REQ_CLIENT_AUTHOR_004 = "cccd79f81439ff794cf5ac5f7bff9121e257f399829e472c7a14d3e86fe76984";
  private static final String multipleIdJson = "[\"REQ\",\"" + REQ_CLIENT_SUBSCRIBER_004 + "\",{\"ids\":[\"" + REQ_CLIENT_EVENT_ID_004_a + "\",\"" + REQ_CLIENT_EVENT_ID_004_b + "\"],\"authors\":[\"" + REQ_CLIENT_AUTHOR_004 + "\"]}]";

  private static final String REQ_CLIENT_SUBSCRIBER_005 = "ReqClientComponent-OVERLAP-005";
  private static final String REQ_CLIENT_EVENT_ID_005_a = "b308ee86a086ff9d7435197c8d0d92412d141653e79535e2805cf15617a699d6";
  private static final String REQ_CLIENT_EVENT_ID_005_b = "e69b40aa10f4ac9cc654f25f564b2ea6e6d73bc9a88848e80c0a82cfaf73f06a";
  private static final String REQ_CLIENT_AUTHOR_005 = "bbbd79f81439ff794cf5ac5f7bff9121e257f399829e472c7a14d3e86fe76984";
  private static final String overlappingIdJson = "[\"REQ\",\"" + REQ_CLIENT_SUBSCRIBER_005 + "\",{\"ids\":[\"" + REQ_CLIENT_EVENT_ID_005_a + "\",\"" + REQ_CLIENT_EVENT_ID_005_b + "\"],\"authors\":[\"" + REQ_CLIENT_AUTHOR_005 + "\"]}]";

  private static final String REQ_CLIENT_SUBSCRIBER_999 = "ReqClientComponent-NONE-001";
  private static final String none = "[\"REQ\",\"" + REQ_CLIENT_SUBSCRIBER_999 + "\",{}]";

  @Autowired
  public NostrWebSocketClient(ConfigurableApplicationContext applicationContext, @Value("${relayUrl}") String relayUrl) {
    super(applicationContext, relayUrl);
  }

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
//    sendRequestMessage(idReqJson, REQ_CLIENT_SUBSCRIBER_001);
//    sendRequestMessage(authorReqJson, REQ_CLIENT_SUBSCRIBER_002);
//    sendRequestMessage(bothReqJson, REQ_CLIENT_SUBSCRIBER_003);
//    sendRequestMessage(multipleIdJson, REQ_CLIENT_SUBSCRIBER_004);
    sendRequestMessage(overlappingIdJson, REQ_CLIENT_SUBSCRIBER_005);
//    sendRequestMessage(none, REQ_CLIENT_SUBSCRIBER_999);
  }
}

