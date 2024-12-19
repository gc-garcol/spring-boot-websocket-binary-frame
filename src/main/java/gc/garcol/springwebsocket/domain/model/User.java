package gc.garcol.springwebsocket.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author thaivc
 * @since 2024
 */
@Getter
@Setter
@Accessors(fluent = true)
@ToString
public class User {

  private Long id;
  private String name;

}
