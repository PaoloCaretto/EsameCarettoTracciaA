package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class ClientHandler implements Runnable{
    private Socket clientSocket;
    private List<Piatto> piattoList;

    public ClientHandler(Socket clientSocket, List<Piatto> piattoList) {
        this.clientSocket = clientSocket;
        this.piattoList= piattoList;
    }

    public void run() {
        try {
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            while (true) {
                
                String command = reader.readLine();
                String response = processCommand(command);

                writer.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String createJsonResponse(List<Piatto> piattoList) {
        Gson gson = new Gson();
        return gson.toJson(piattoList);
    }

    private String processCommand(String command) {
        if (command.equals("all")) {
            return createJsonResponse(piattoList);
        } else if (command.equals("all_vegans")) {
            List<Piatto> veganPiattoList = new ArrayList<>();
            for (Piatto piatto : piattoList) {
                if (piatto.isVegan()) {
                    veganPiattoList.add(piatto);
                }
            }
            return createJsonResponse(veganPiattoList);
        }else if (command.equals("more_caloric")) {
            Piatto moreCaloricPiatto = Collections.max(piattoList, (p1, p2) -> Double.compare(p1.getCalories(), p2.getCalories()));
            return createJsonResponse(Collections.singletonList(moreCaloricPiatto));
        }  else {
            return "Invalid command";
        }
    }
}

