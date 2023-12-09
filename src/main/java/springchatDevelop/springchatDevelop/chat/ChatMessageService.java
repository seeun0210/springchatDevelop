package springchatDevelop.springchatDevelop.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springchatDevelop.springchatDevelop.chatroom.ChatRoomService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        var chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
                .orElseThrow(() -> new RuntimeException("Failed to get chat room ID"));

        chatMessage.setChatId(chatId);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(id -> chatMessageRepository.findByChatId(id))
                .orElse(new ArrayList<>());
    }

}
