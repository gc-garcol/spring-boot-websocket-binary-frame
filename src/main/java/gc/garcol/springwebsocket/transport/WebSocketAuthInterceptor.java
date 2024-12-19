package gc.garcol.springwebsocket.transport;

import gc.garcol.springwebsocket.domain.UserService;
import gc.garcol.springwebsocket.domain.constant.ServerConstant;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * @author thaivc
 * @since 2024
 */
@Component
@RequiredArgsConstructor
public class WebSocketAuthInterceptor implements HandshakeInterceptor {

  private final UserService userService;

  @Override
  public boolean beforeHandshake(
      ServerHttpRequest request,
      ServerHttpResponse response,
      WebSocketHandler wsHandler,
      Map<String, Object> attributes
  ) throws Exception {
    ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
    String token = servletRequest
        .getServletRequest()
        .getQueryString()
        .split(ServerConstant.WS_HANDSHAKE_TOKEN + "=")[1];

    if (token == null) {
      return false;
    }

    attributes.put(ServerConstant.ATTRIBUTE_USER, userService.parseUser(token));

    return true;
  }

  @Override
  public void afterHandshake(
      ServerHttpRequest request,
      ServerHttpResponse response,
      WebSocketHandler wsHandler,
      Exception exception
  ) {
    // do something
  }
}
