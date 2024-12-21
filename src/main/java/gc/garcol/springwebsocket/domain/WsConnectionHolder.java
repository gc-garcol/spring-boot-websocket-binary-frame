package gc.garcol.springwebsocket.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author thaivc
 * @since 2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WsConnectionHolder {

  public static final Map<String, WebSocketSession> WS_CONNECTIONS = new ConcurrentHashMap<>();

}
