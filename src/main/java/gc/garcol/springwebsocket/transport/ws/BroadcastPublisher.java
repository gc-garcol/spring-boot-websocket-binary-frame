package gc.garcol.springwebsocket.transport.ws;

import gc.garcol.springwebsocket.domain.constant.ServerConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author thaivc
 * @since 2025
 */
@Component
@RequiredArgsConstructor
public class BroadcastPublisher {

  private final RedisTemplate<String, byte[]> redisTemplate;

  public void publish(byte[] message) {
    redisTemplate.convertAndSend(ServerConstant.WS_TOPIC_BROADCAST, message);
  }
}
