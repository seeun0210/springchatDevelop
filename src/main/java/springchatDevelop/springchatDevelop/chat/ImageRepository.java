package springchatDevelop.springchatDevelop.chat;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.*;

public interface ImageRepository extends MongoRepository<ChatMessage, String> {
    // 이미지와 관련된 커스텀한 쿼리 메서드 등을 추가할 수 있음
}