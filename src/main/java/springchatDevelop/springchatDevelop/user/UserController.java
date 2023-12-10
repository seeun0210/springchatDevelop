package springchatDevelop.springchatDevelop.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public User addUser(@Payload User user) {
        log.info("Received addUser request: {}", user);
        service.saveUser(user);
        return user;
    }
    @MessageMapping("/user.disconnectUser/{userId}")
    @SendTo("/user/topic")
    public User disconnect(
            @Payload User user,
            @DestinationVariable String userId
    ){
        service.disconnect(user);
        log.info("disconnect user::{}",user);
        return user;
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>>findConnectedUsers(){
        return ResponseEntity.ok(service.findConnectedUsers());
    }
}
