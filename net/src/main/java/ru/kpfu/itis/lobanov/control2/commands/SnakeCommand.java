package ru.kpfu.itis.lobanov.control2.commands;

import javafx.scene.control.TextArea;
import ru.kpfu.itis.lobanov.control2.BotApplication;
import ru.kpfu.itis.lobanov.control2.game.GameApplication;
import ru.kpfu.itis.lobanov.control2.utils.BotResponsesStringsProvider;
import ru.kpfu.itis.lobanov.control2.utils.CommandInfoProvider;
import ru.kpfu.itis.lobanov.fx.chat.ChatApplication;

public class SnakeCommand implements Command {
    private final TextArea chat;

    public SnakeCommand(TextArea chat) {
        this.chat = chat;
    }

    @Override
    public void execute() {
        GameApplication chatApplication = new GameApplication();
        try {
            chatApplication.start(BotApplication.getPrimaryStage());
        } catch (Exception e) {
            chat.appendText(BotResponsesStringsProvider.GAME_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public String getName() {
        return CommandInfoProvider.GAME_COMMAND_NAME;
    }

    @Override
    public String getDescription() {
        return CommandInfoProvider.GAME_COMMAND_DESCRIPTION;
    }
}
