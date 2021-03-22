package bll;

import be.Message;
import dal.ChatDataContext;
import dal.XMLMessageRepository;

public class MechaChatLogicFacade implements IMechaChatLogicFacade {

    private static IMechaChatLogicFacade instance;
    private final ChatDataContext dataContext = new ChatDataContext(new XMLMessageRepository());
    private final MessageFactory messageFactory = new MessageFactory();

    private MechaChatLogicFacade()
    { }

    public static IMechaChatLogicFacade getInstance() {
        if (instance == null) {
            instance = new MechaChatLogicFacade();
        }

        return instance;
    }

    @Override
    public Message logMessage(String msg) {
        int id = dataContext.getNextId();
        Message message = messageFactory.createMessage(id, msg);
        dataContext.saveMessage(message);
        return message;
    }
}
