//package sample.webflux.websocket.netty.client;
//
//import reactor.core.publisher.Mono;
//
//public class ClientLogic {
//
//  public void doLogic(WebSocketHandler webSocketHandler, String message) {
//    Mono
//        .fromRunnable(
//            () -> webSocketHandler.send(message)
//        )
//        .thenMany(webSocketHandler.receive())
//        .doOnNext(
//            System.out::println
//        )
//        .subscribe();
//  }
//}
