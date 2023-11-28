package ru.kpfu.itis.lobanov.control2.utils;

import ru.kpfu.itis.lobanov.control2.exceptions.EmptyInputException;
import ru.kpfu.itis.lobanov.control2.exceptions.InvalidCommandException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputParser {
    private static String commandName;
    private static List<String> args;

    public static void parse(String input) throws EmptyInputException, InvalidCommandException {
        if (input == null) throw new NullPointerException();

        List<String> splitString = new ArrayList<>(Arrays.asList(input.split(" ")));
        if (splitString.isEmpty()) throw new EmptyInputException();

        commandName = splitString.get(0);
        if (commandName.isEmpty() && splitString.size() > 1) throw new InvalidCommandException(input);
        args = parseArgs(splitString);
    }

    private static List<String> parseArgs(List<String> splitInput) {
        List<String> args = new ArrayList<>();
        for (int i = 1; i < splitInput.size(); i++) {
            args.add(splitInput.get(i));
        }
        return args;
    }


    public static String getCommandName() {
        return commandName;
    }

    public static List<String> getArgs() {
        return args;
    }

    private InputParser() {
    }
}
