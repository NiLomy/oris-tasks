package ru.kpfu.itis.lobanov.homeworks.task1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HttpClientImpl httpClient;
        if (args.length != 0) {
            httpClient = new HttpClientImpl(args[0]);
        } else {
            httpClient = new HttpClientImpl();
        }
        Scanner scanner = new Scanner(System.in);

        System.out.println("Commands to use: get, post, put, delete and exit");
        System.out.println("Enter your link, parameters (k1=v1 k2=v2 ... separated by spaces) and command in different lines");

        while (true) {
            String link = scanner.nextLine().trim();
            String paramsInput = scanner.nextLine().trim();
            String command = scanner.nextLine().trim().toLowerCase();

            try {
                Map<String, String> params = getParams(paramsInput);
                switch (command) {
                    case "get":
                        System.out.println(httpClient.get(link, params));
                        break;
                    case "post":
                        System.out.println(httpClient.post(link, params));
                        break;
                    case "put":
                        System.out.println(httpClient.put(link, params));
                        break;
                    case "delete":
                        System.out.println(httpClient.delete(link, params));
                        break;
                    case "exit":
                        System.exit(0);
                        break;
                    default:
                        System.err.println("You entered wrong command");
                        break;
                }
            } catch (IOException | IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
            System.out.println();
        }
    }

    private static Map<String, String> getParams(String paramsInput) throws IllegalArgumentException {
        Map<String, String> params = new HashMap<>();
        if (paramsInput == null || paramsInput.length() == 0) return params;
        String[] paramsPairs = paramsInput.split(" ");
        for (String pair :
                paramsPairs) {
            String[] pairValues = pair.split("=");
            if (pairValues.length != 2) throw new IllegalArgumentException("Incorrect input of parameters");
            params.put(pairValues[0], pairValues[1]);
        }
        return params;
    }
}
