package dal;

import be.Message;
import be.MessageList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class XMLMessageRepository implements IMessageRepository {
    @Override
    public void save(Message message) {
        try {
            MessageList ml = load();
            ml.addMessage(message);

            JAXBContext jc = JAXBContext.newInstance(MessageList.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            File xml = new File("messages.xml");
            marshaller.marshal(ml, xml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getNextId() {
        Message[] messages = load().getMessages();
        Optional<Message> msg = Arrays.asList(messages).stream().max(Comparator.comparing(Message::getId));

        if(msg.isEmpty()) {
            return 1;
        } else {
            return msg.get().getId() + 1;
        }
    }

    private MessageList load() {
        try {
            JAXBContext jc = JAXBContext.newInstance(MessageList.class);

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            File xml = new File("messages.xml");
            MessageList root = (MessageList) unmarshaller.unmarshal(xml);
            return root;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return new MessageList();
    }
}
