package dal;

import be.Message;

public class ChatDataContext {

    private IMessageRepository repositoryStrategy;

    public ChatDataContext(IMessageRepository repositoryStrategy) {
        this.repositoryStrategy = repositoryStrategy;
    }

    public void setRepositoryStrategy(IMessageRepository repositoryStrategy) {
        this.repositoryStrategy = repositoryStrategy;
    }

    public void saveMessage(Message message) {
        repositoryStrategy.save(message);
    }

    public int getNextId() {
        return repositoryStrategy.getNextId();
    }

    public Message[] getAllMessages() {
        return repositoryStrategy.getMessages();
    }
}
