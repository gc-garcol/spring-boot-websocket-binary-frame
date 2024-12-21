package gc.garcol.springwebsocket.transport;

import gc.garcol.springwebsocket.domain.InboundRequestHandler;
import gc.garcol.springwebsocket.domain.WsConnectionHolder;
import gc.garcol.springwebsocket.domain.constant.ServerConstant;
import gc.garcol.springwebsocket.domain.model.User;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

/**
 * @author thaivc
 * @since 2024
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BinaryWsHandler extends BinaryWebSocketHandler {

  protected final InboundRequestHandler inboundRequestHandler;

  @Override
  protected void handleBinaryMessage(
      WebSocketSession session, BinaryMessage message
  ) throws Exception {

    User user = (User) session.getAttributes().get(ServerConstant.ATTRIBUTE_USER);

    if (user == null) {
      session.close(CloseStatus.NOT_ACCEPTABLE);
      return;
    }

    handleMessage(session, user, message);
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    User user = (User) session.getAttributes().get(ServerConstant.ATTRIBUTE_USER);

    if (user == null) {
      session.close(CloseStatus.NOT_ACCEPTABLE);
      return;
    }

    WsConnectionHolder.WS_CONNECTIONS.put(session.getId(), session);
    log.info("Websocket connection connected: {}", user);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    WsConnectionHolder.WS_CONNECTIONS.remove(session.getId());
    log.info("Websocket connection closed");
  }

  private void handleMessage(
      WebSocketSession session,
      User user,
      BinaryMessage message
  ) throws IOException {

    log.info("Received message from user: {}", user);
    String response = String.format("[%s]: %s",
        user.toString(),
        new String(message.getPayload().array())
    );
    session.sendMessage(new BinaryMessage(response.getBytes()));
  }
}
