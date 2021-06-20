package ru.geekbrains.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private List<ClientHandler> clients; //создаем список клиентов

    public Server() {
        try {
            this.clients = new ArrayList<ClientHandler>();
            ServerSocket serverSocket = new ServerSocket(8189);
            System.out.println("Сервер запущен. Ожидаем подключение клиентов..");

            while (true) { //подключение клиентов
                final Socket socket = serverSocket.accept(); //ожидание подключения клиента
                System.out.println("Клиент подключился ");
                ClientHandler client = new ClientHandler(this, socket); //запускаем обработчик клиента, работаем с клиентом
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void subscribe(ClientHandler client) {
        broadcastMessage("Добавлен новый клиент " + client.getUserName());
        clients.add(client); //добавляем клиента в список
    }

    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client); //удаляем клиента из списка
        broadcastMessage("Клиент " + client.getUserName() + " вышел из чата");
    }

    public void broadcastMessage(String message) {
        //рассылка сообщений всем авторизованным клиентам
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public boolean checkUsername(String username) { //проверка логина на дублирование
        boolean res = true;
        for (ClientHandler client : clients) {
            if (username.equals(client.getUserName())) {
                res = false;
            }
        }
        return res;
    }
}
