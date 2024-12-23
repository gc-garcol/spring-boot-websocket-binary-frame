package gc.garcol.springwebsocket.metric;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author thaivc
 * @since 2024
 */
@Configuration
@RequiredArgsConstructor
public class WebsocketMetric {

  private final MeterRegistry meterRegistry;

  @Bean
  public Timer wsTimer() {
    return Timer.builder("websocket.binary.request")
        .description("Websocket binary request")
        .publishPercentileHistogram()
        .publishPercentiles(0.5, 0.95, 0.99)
        .register(meterRegistry);
  }
}
