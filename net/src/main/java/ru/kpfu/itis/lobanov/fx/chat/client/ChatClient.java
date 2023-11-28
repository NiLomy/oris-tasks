package ru.kpfu.itis.lobanov.fx.chat.client;

import ru.kpfu.itis.lobanov.fx.chat.ChatApplication;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatClient {

    private Socket socket;
    private final ChatApplication application;
    private  ClientThread thread;

    public ChatClient(ChatApplication application) {
        this.application = application;
    }

    public void sendMessage(String message) {
        try {
            thread.getOutput().write(message);
            thread.getOutput().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        String host = application.getUserConfig().getHost();
        int port = application.getUserConfig().getPort();

        try {
            socket = new Socket(host, port);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

            thread = new ClientThread(input, output, this);
            new Thread(thread).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ChatApplication getApplication() {
        return application;
    }

    static class ClientThread implements Runnable {

        private BufferedReader input;
        private BufferedWriter output;
        private ChatClient chatClient;

        public ClientThread(BufferedReader input, BufferedWriter output, ChatClient chatClient) {
            this.input = input;
            this.output = output;
            this.chatClient = chatClient;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String message = input.readLine();
                    if (message == null) break;
                    chatClient.application.appendMessage(message);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public BufferedReader getInput() {
            return input;
        }

        public void setInput(BufferedReader input) {
            this.input = input;
        }

        public BufferedWriter getOutput() {
            return output;
        }

        public void setOutput(BufferedWriter output) {
            this.output = output;
        }

        public ChatClient getChatClient() {
            return chatClient;
        }

        public void setChatClient(ChatClient chatClient) {
            this.chatClient = chatClient;
        }
    }
}
