package gc.garcol.springwebsocket.domain;

import gc.garcol.springwebsocket.domain.model.User;
import org.springframework.stereotype.Service;

/**
 * @author thaivc
 * @since 2024
 */
@Service
public class UserService {

  public User parseUser(String token) {
    String[] data = token.split(":");
    return new User().id(Long.parseLong(data[0])).name(data[1]);
  }

}
