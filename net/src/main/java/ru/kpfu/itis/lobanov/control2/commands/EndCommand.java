package ru.kpfu.itis.lobanov.control2.commands;

import javafx.scene.control.TextArea;
import ru.kpfu.itis.lobanov.control2.utils.BotResponsesStringsProvider;
import ru.kpfu.itis.lobanov.control2.utils.CommandInfoProvider;

public class EndCommand implements Command {
    private final TextArea chat;

    public EndCommand(TextArea chat) {
        this.chat = chat;
    }

    @Override
    public void execute() {
        chat.appendText(BotResponsesStringsProvider.GOODBYE_MESSAGE);
        System.exit(0);
    }

    @Override
    public String getName() {
        return CommandInfoProvider.END_COMMAND_NAME;
    }

    @Override
    public String getDescription() {
        return CommandInfoProvider.END_COMMAND_DESCRIPTION;
    }
}
