package gc.garcol.springwebsocket.transport.ws;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author thaivc
 * @since 2024
 */
@EnableWebSocket
@Configuration
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

  private final BinaryWsHandler binaryWsHandler;
  private final WebSocketAuthInterceptor webSocketAuthInterceptor;

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry
        .addHandler(binaryWsHandler, "/ws")
        .setAllowedOrigins("*")
        .addInterceptors(webSocketAuthInterceptor);
  }
}
