package com.spiderweb.vchat.socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 5000);
             BufferedReader reader = new BufferedReader( new InputStreamReader(socket.getInputStream()));
             BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println(" Connected to the Server");

            String userMessage;

            while (true) {
                System.out.print("Client :");
                userMessage = clientInput.readLine();
                writer.println(userMessage);

                if(userMessage.equalsIgnoreCase("exit")){
                    break;
                }

                String serverResponse = reader.readLine();
                System.out.println("Server: " + serverResponse);
            }

            System.out.println("Clinet Shutting Down");


        } catch (Exception re) {
            System.out.println("Server unavailable");
        } finally {
            System.out.println("Bye");
        }
    }

}
