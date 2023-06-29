package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPServer
{
    private static final int PORT = 9999;
    private static List<Piatto> piattoList = new ArrayList<>();

    public static void main( String[] args )
    {
       populatePiattoList();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, piattoList);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void populatePiattoList() {
        piattoList.add(new Piatto(3, "Risotto alla Milanese", "Il piatto...", 325.94, false));
        piattoList.add(new Piatto(34, "Costata", "Il II famoso piatto...", 133.0, false));
        piattoList.add(new Piatto(40, "Insalata", "Una semplice insalata...", 80.0, true));
    }
}
