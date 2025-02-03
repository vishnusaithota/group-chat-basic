package com.spiderweb.vchat.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is waiting for client connection...");

            try (Socket socket = serverSocket.accept();
                 BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

                System.out.println("Client connected.");

                String clientMessage;
                while ((clientMessage = input.readLine()) != null) {
                    System.out.println("Client: " + clientMessage);

                    if ("exit".equalsIgnoreCase(clientMessage)) {
                        System.out.println("Client disconnected.");
                        break;
                    }

                    System.out.print("Server: ");
                    String response = userInput.readLine();
                    output.println(response);
                }
            }
            System.out.println("Server shutting down...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
