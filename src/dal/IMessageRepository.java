package dal;

import be.Message;

public interface IMessageRepository {
    void save(Message message);
    int getNextId();
}
