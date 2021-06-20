package ru.geekbrains.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    TextArea chatWindow; //многострочное текстовое поле для вывода сообщений в чате

    @FXML
    TextField messageWindow; //однострочное текстовое поле для написания сообщения серверу

    @FXML
    HBox authPanel, msgPanel;

    @FXML
    TextField userName; //однострочное текстовое поле для авторизации

    private Socket socket; //соединение с сервером
    private DataInputStream in; //входящий поток для получения сообщений
    private DataOutputStream out; //исходящий поток для отправки сообщений

    public void sendMessage() { //метод для отправки сообщений на сервер
        try {
            out.writeUTF(messageWindow.getText()); //с однострочного текстового поля отправляем сообщение по исходящему потоку
            messageWindow.clear();//текстовое окно очищаем
            messageWindow.requestFocus();//перекидываем курсор на текстовое окно
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void auth() { //метод для авторизации пользователя
        connect();
        try {
            out.writeUTF("/auth " + userName.getText()); //в окне для авторизации пишем /auth имя пользователя введенное клиентом
            userName.clear(); //очищаем поле
        } catch (IOException e) {
            messageError("Невозможно отправить запрос авторизации на сервер");
        }
    }

    public void connect() { //соединение с сервером
        if (socket != null && !socket.isClosed()) { //проверка на активное соединение, чтобы не открывать несколько соединений одному пользователю
            return;
        }
        try {
            socket = new Socket("localhost", 8189); //подключение к серверу
            in = new DataInputStream(socket.getInputStream()); //открываем входящий и исходящий потоки
            out = new DataOutputStream(socket.getOutputStream());
            Thread readThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            String inputMessage = in.readUTF(); //ждем сообщения от сервера и складываем в строку
                            if (inputMessage.equals("/authok")) { //если авторизация прошла успешно то скрываем панель авторизации и показываем окно чата
                                msgPanel.setVisible(true);
                                msgPanel.setManaged(true);
                                authPanel.setVisible(false);
                                authPanel.setManaged(false);
                                break;
                            }
                            chatWindow.appendText(inputMessage + "\n");//добавляем сообщение в чат
                        }
                        while (true) {
                            String inputMessage = in.readUTF(); //ждем сообщения от сервера и складываем в строку
                            chatWindow.appendText(inputMessage + "\n");//добавляем сообщение в чат
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            readThread.start();
        } catch (IOException e) {
            System.out.println("Невозможно подключиться к серверу");
        }
    }

    public void messageError(String message) { //сообщения об ошибках
        new Alert(Alert.AlertType.ERROR, message, ButtonType.OK).showAndWait();
    }
}
