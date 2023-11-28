package ru.kpfu.itis.lobanov.udp;

import java.io.IOException;

public class ClientTest {

    public static void main(String[] args) {
        GreetingServer server = new GreetingServer();
        server.start();
//
//        try (GreetingClient client = new GreetingClient()) {
//            System.out.println(client.send("Hello"));
//            System.out.println(client.send("hellO"));
//            System.out.println(client.send("1234"));
//            System.out.println(client.send("bye"));
//            System.out.println(client.send("hello"));
//        }
    }
}
