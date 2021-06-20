package ru.geekbrains.server;

import javax.print.DocFlavor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler {

    //работа с клиентом

    //запоминаем сокет и входящие/исходящие потоки
    private Server server; //ссылка на сервер, на котором сидим
    private Socket socket;
    private String userName;
    private DataInputStream in; //обработчики потоков
    private DataOutputStream out;

    public String getUserName() {
        return userName;
    }

    public ClientHandler(final Server server, Socket socket) {
        //обработчик клиентов
        try {
            this.server = server; //запомнили сервер
            this.socket = socket; //запомнили входящий сокет и открыли входящие и исходящие потоки для обмена
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            Thread clientThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        while (true) { //ждем авторизации клиента
                            String inputMessage = in.readUTF(); //получаем сообщение от клиента
                            if (inputMessage.startsWith("/auth ")) { //если начинается с /auth,
                                userName = inputMessage.split("\\s+", 2)[1]; //то разделяем сообщение по пробелу и берем его только первый элемент
                                if (server.checkUsername(userName)) {
                                    sendMessage("/authok"); //отправляем команду, что авторизация прошла успешно
                                    sendMessage("Вы зашли в чат под именем " + userName);
                                    server.subscribe(ClientHandler.this);//добавляем клиента в список рассылки
                                    break;
                                } else {
                                    sendMessage("Логин уже занят");
                                }
                            } else {
                                sendMessage("Необходимо авторизоваться");
                            }
                        }
                        while (true) { //рассылка сообщений авторизованным клиентам
                            String inputMessage = in.readUTF(); //ждем сообщение от клиента, получаем сообщение в байтах и собираем в строку
                            if (inputMessage.startsWith("/")) {
                                continue;
                            }
                            server.broadcastMessage(userName + ": " + inputMessage);//расслыка всем клиентам
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        server.unsubscribe(ClientHandler.this); //удаляем клиента из списка
                    }
                }
            });
            clientThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) { //метод для отправки сообщений клиенту
        try {
            out.writeUTF(message);// клиенту отправляем его же сообщение
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
