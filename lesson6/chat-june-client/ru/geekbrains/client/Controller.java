package ru.geekbrains.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TextArea chatWindow;

    @FXML
    TextField messageWindow;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Thread readThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            String inputMessage = in.readUTF();
                            chatWindow.appendText(inputMessage + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            readThread.start();
        } catch (IOException e) {
            System.out.println("Невозможно подключиться к серверу");
            System.exit(0);
        }
    }

    public void sendMessage() {
        try {
            out.writeUTF(messageWindow.getText());
            messageWindow.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
