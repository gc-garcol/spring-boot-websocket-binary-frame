package gc.garcol.springwebsocket.transport.ws;

import gc.garcol.springwebsocket.domain.WsConnectionHolder;
import gc.garcol.springwebsocket.domain.constant.ServerConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;

/**
 * @author thaivc
 * @since 2025
 */
@Slf4j
@Component
public class BroadcastReceiver implements MessageListener {

  public BroadcastReceiver(RedisMessageListenerContainer listenerContainer) {
    listenerContainer.addMessageListener(this, new ChannelTopic(ServerConstant.WS_TOPIC_BROADCAST));
  }

  @Override
  public void onMessage(Message message, byte[] pattern) {
    var emitMessage = new BinaryMessage(message.getBody());
    WsConnectionHolder.WS_CONNECTIONS.values().forEach(session -> {
      try {
        session.sendMessage(emitMessage);
      } catch (Exception e) {
        log.error("Failed to send message to session: {}", session.getId(), e);
      }
    });
  }
}
