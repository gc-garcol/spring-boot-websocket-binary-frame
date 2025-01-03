package gc.garcol.springwebsocket.transport.rest;

import static gc.garcol.springwebsocket.domain.constant.ServerConstant.WS_HANDSHAKE_TOKEN;

import gc.garcol.springwebsocket.domain.UserService;
import gc.garcol.springwebsocket.domain.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thaivc
 * @since 2025
 */
@CrossOrigin(originPatterns = "http://localhost:*", allowCredentials = "true")
@RestController
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;

  @PostMapping("/login")
  public ResponseEntity<?> login(
      @RequestBody Login login,
      HttpServletResponse response
  ) {
    Cookie cookie = new Cookie(WS_HANDSHAKE_TOKEN,
        userService.generateToken(new User().id(1L).name(login.username()))
    );
    cookie.setMaxAge(60 * 60);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setSecure(false);
    response.addCookie(cookie);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletResponse response) {
    Cookie cookie = new Cookie(WS_HANDSHAKE_TOKEN, null);
    cookie.setMaxAge(0);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    response.addCookie(cookie);
    return ResponseEntity.ok().build();
  }
}
