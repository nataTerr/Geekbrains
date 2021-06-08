package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextArea chatWindow;


    @FXML
    TextField messageWindow;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void enterMessage(ActionEvent actionEvent) {

        if(!messageWindow.getText().isEmpty()) {
            chatWindow.appendText(messageWindow.getText() + "\n");
            messageWindow.clear();
        }
    }

    public void enter(ActionEvent actionEvent) {
        if(!messageWindow.getText().isEmpty()) {
            chatWindow.appendText(messageWindow.getText() + "\n");
            messageWindow.clear();
        }
    }
}
