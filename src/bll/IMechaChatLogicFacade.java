package bll;

import be.Message;

public interface IMechaChatLogicFacade {
    Message logMessage(String msg);

    Message[] loadMessages();
}
