package bll;

import be.Message;

public class MessageFactory {

    public Message createMessage(int id, String message) {
        return new Message(id, message);
    }

}
