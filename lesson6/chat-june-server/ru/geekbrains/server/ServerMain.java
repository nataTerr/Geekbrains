package ru.geekbrains.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {

    public static ArrayList<Socket> clients = new ArrayList<Socket>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8189);
            System.out.println("Сервер запущен. Ожидаем подключение клиентов..");

            while (true) {
                final Socket socket = serverSocket.accept();
                System.out.println("Клиент подключился " + socket);

                Thread clientThread = new Thread(new Runnable() {
                    public void run() {
                        try {

                            DataInputStream in = new DataInputStream(socket.getInputStream());
                            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                            while (true) {
                                String inputMessage = in.readUTF();
                                System.out.println(inputMessage);
                                out.writeUTF("ECHO: " + inputMessage);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
