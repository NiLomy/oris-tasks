package ru.kpfu.itis.lobanov.control2.commands;

import javafx.scene.control.TextArea;
import ru.kpfu.itis.lobanov.control2.BotApplication;
import ru.kpfu.itis.lobanov.control2.utils.BotResponsesStringsProvider;
import ru.kpfu.itis.lobanov.control2.utils.CommandInfoProvider;
import ru.kpfu.itis.lobanov.fx.chat.ChatApplication;

public class ChatCommand implements Command {
    private final TextArea chat;

    public ChatCommand(TextArea chat) {
        this.chat = chat;
    }

    @Override
    public void execute() {
        ChatApplication chatApplication = new ChatApplication();
        try {
            chatApplication.start(BotApplication.getPrimaryStage());
        } catch (Exception e) {
            chat.appendText(BotResponsesStringsProvider.CHAT_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public String getName() {
        return CommandInfoProvider.CHAT_COMMAND_NAME;
    }

    @Override
    public String getDescription() {
        return CommandInfoProvider.CHAT_COMMAND_DESCRIPTION;
    }
}
