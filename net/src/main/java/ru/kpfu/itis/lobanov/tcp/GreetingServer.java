package ru.kpfu.itis.lobanov.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetingServer extends Thread {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private int port;

    public GreetingServer(int port) {
        this.port = port;
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(port);

            clientSocket = serverSocket.accept();

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;

            while ((message = in.readLine()) != null) {
                if ("hello".equalsIgnoreCase(message.trim())) {
                    out.println("Hello from server");
                } else if ("bye".equalsIgnoreCase(message.trim())) {
                    out.println("Bye!");

                    in.close();
                    out.close();
                    serverSocket.close();
                    clientSocket.close();
                    break;
                } else {
                    out.println("?");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
