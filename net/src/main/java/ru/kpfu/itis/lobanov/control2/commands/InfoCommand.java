package ru.kpfu.itis.lobanov.control2.commands;

import javafx.scene.control.TextArea;
import ru.kpfu.itis.lobanov.control2.utils.CommandInfoProvider;
import ru.kpfu.itis.lobanov.control2.utils.CommandRepository;

import java.util.Arrays;

public class InfoCommand implements Command {
    private final TextArea chat;

    public InfoCommand(TextArea chat) {
        this.chat = chat;
    }

    @Override
    public void execute() {
        Command[] commands = CommandRepository.getCommands(chat);
        int maxLength = Arrays.stream(commands)
                .map(Command::getName)
                .mapToInt(String::length)
                .max()
                .getAsInt();
        for (Command command :
                commands) {
            chat.appendText(command.getName());
            for (int i = command.getName().length(); i < maxLength + 1; i++) {
                chat.appendText("  ");
            }
            chat.appendText(command.getDescription() + "\n");
        }
    }

    @Override
    public String getName() {
        return CommandInfoProvider.INFO_COMMAND_NAME;
    }

    @Override
    public String getDescription() {
        return CommandInfoProvider.INFO_COMMAND_DESCRIPTION;
    }
}
