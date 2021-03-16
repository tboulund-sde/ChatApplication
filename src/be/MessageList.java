package be;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement(name="messages")
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageList {
    @XmlElement(name="message")
    private Message[] messages;

    public MessageList() {
        this.messages = new Message[0];
    }

    public MessageList(Message... messages) {
        this.messages = messages;
    }

    public Message[] getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        Message[] msgs = Arrays.copyOf(messages, messages.length + 1);
        msgs[messages.length] = message;
        messages = msgs;
    }
}
