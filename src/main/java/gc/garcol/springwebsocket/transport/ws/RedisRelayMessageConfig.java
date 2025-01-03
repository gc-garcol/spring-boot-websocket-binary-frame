package gc.garcol.springwebsocket.transport.ws;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author thaivc
 * @since 2025
 */
@Configuration
@RequiredArgsConstructor
public class RedisRelayMessageConfig {

  @Bean
  public RedisTemplate<String, byte[]> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, byte[]> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);

    // Key serializer
    template.setKeySerializer(RedisSerializer.string());
    template.setHashKeySerializer(RedisSerializer.string());

    // Value serializer for byte arrays
    template.setValueSerializer(RedisSerializer.byteArray());
    template.setHashValueSerializer(RedisSerializer.byteArray());

    template.afterPropertiesSet();
    return template;
  }

  @Bean
  public RedisMessageListenerContainer redisMessageListenerContainer(
      RedisConnectionFactory connectionFactory) {
    var container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    return container;
  }
}
