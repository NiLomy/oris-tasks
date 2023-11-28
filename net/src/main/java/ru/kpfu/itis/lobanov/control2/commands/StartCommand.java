package ru.kpfu.itis.lobanov.control2.commands;

import javafx.scene.control.TextArea;
import ru.kpfu.itis.lobanov.control2.utils.BotResponsesStringsProvider;
import ru.kpfu.itis.lobanov.control2.utils.CommandInfoProvider;

public class StartCommand implements Command {
    private final TextArea chat;

    public StartCommand(TextArea chat) {
        this.chat = chat;
    }

    @Override
    public void execute() {
        chat.appendText(BotResponsesStringsProvider.HELLO_MESSAGE);
    }

    @Override
    public String getName() {
        return CommandInfoProvider.START_COMMAND_NAME;
    }

    @Override
    public String getDescription() {
        return CommandInfoProvider.START_COMMAND_DESCRIPTION;
    }
}
