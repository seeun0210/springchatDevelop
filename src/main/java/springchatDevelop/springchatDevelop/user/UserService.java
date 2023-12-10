package springchatDevelop.springchatDevelop.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final MongoTemplate mongoTemplate;
    private final UserRepository userRepository;

    @Transactional
    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }

    @Transactional
    public void disconnect(User user) {
        Optional<User> storedUserOptional = userRepository.findById(user.getNickName());
        storedUserOptional.ifPresent(storedUser -> {
            storedUser.setStatus(Status.OFFLINE);
            userRepository.save(storedUser);
            log.info("disconnected User::{}",storedUser);
        });
    }

    public List<User> findConnectedUsers() {
        // 이 부분은 트랜잭션이 필요하지 않을 수 있습니다.
        return userRepository.findAllByStatus(Status.ONLINE);
    }
}