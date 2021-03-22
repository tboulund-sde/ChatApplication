package sample;

import be.Message;
import bll.IMechaChatLogicFacade;
import bll.MechaChatLogicFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {

    private ObservableList<Message> messages = FXCollections.observableArrayList();
    private IMechaChatLogicFacade chatFacade = MechaChatLogicFacade.getInstance();

    @FXML
    private ListView<Message> lstMessages;

    @FXML
    private TextField txtMessage;

    @FXML
    public void initialize() {
        messages.setAll(chatFacade.loadMessages());

        lstMessages.setItems(messages);
        lstMessages.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Message message, boolean empty) {
                super.updateItem(message, empty);
                if(empty || message == null) {
                    setText(null);
                } else {
                    setText(message.getMessage());
                }
            }
        });
    }

    @FXML
    public void send() {
        Message msg = chatFacade.logMessage(txtMessage.getText());
        messages.add(msg);
    }

}
