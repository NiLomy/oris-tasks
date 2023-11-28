package ru.kpfu.itis.lobanov.control2.utils;

import javafx.scene.control.TextArea;
import ru.kpfu.itis.lobanov.control2.commands.Command;
import ru.kpfu.itis.lobanov.control2.exceptions.EmptyInputException;
import ru.kpfu.itis.lobanov.control2.exceptions.InvalidCommandException;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandFactory {
    private final TextArea chat;

    public CommandFactory(TextArea chat) {
        this.chat = chat;
    }

    public Command getCommand(String commandName) throws InvalidCommandException,
            NullPointerException, EmptyInputException {
        final Map<String, Command> commandMap = Arrays.stream(CommandRepository.getCommands(chat))
                .collect(Collectors.toMap(Command::getName, command -> command));

        if (commandName == null) throw new NullPointerException();
        if (commandName.isEmpty()) throw new EmptyInputException();
        Command command = commandMap.get(commandName);
        if (command == null) {
            throw new InvalidCommandException(commandName);
        } else {
            return command;
        }
    }
}
