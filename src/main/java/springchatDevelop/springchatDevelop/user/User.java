package springchatDevelop.springchatDevelop.user;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {
    private String nickName;
    private String fullName;
    private Status status;
}
