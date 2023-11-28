package ru.kpfu.itis.lobanov.control2.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.kpfu.itis.lobanov.control2.commands.Command;
import ru.kpfu.itis.lobanov.control2.exceptions.EmptyInputException;
import ru.kpfu.itis.lobanov.control2.exceptions.InvalidCommandException;
import ru.kpfu.itis.lobanov.control2.utils.BotResponsesStringsProvider;
import ru.kpfu.itis.lobanov.control2.utils.CommandFactory;
import ru.kpfu.itis.lobanov.control2.utils.CommandInfoProvider;
import ru.kpfu.itis.lobanov.control2.utils.InputParser;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;

public class BotPageController {
    @FXML
    private TextArea chat;
    @FXML
    private TextField textInput;
    private int state = 0;
    private CommandFactory factory;
    private final EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.ENTER) {
                String input = textInput.getText();
                if (input != null && !input.isEmpty()) {
                    chat.appendText(String.format(BotResponsesStringsProvider.USER_PRINT, input));
                    try {
                        InputParser.parse(input);
                        String commandName = InputParser.getCommandName();
                        if (commandName.equals(CommandInfoProvider.START_COMMAND_NAME)) {
                            state = 1;
                        }
                        if (state == 1) {
                            Command command = factory.getCommand(commandName);
                            command.execute();
                        } else {
                            chat.appendText(BotResponsesStringsProvider.START_CHAT_MESSAGE);
                        }
                    } catch (InvalidCommandException e) {
                        chat.appendText(BotResponsesStringsProvider.INVALID_COMMAND_MESSAGE);
                    } catch (EmptyInputException ignored) {
                    }

                    textInput.clear();
                    event.consume();
                }
            }
        }
    };

    @FXML
    private void initialize() {
        factory = new CommandFactory(chat);
        textInput.addEventHandler(KEY_PRESSED, eventHandler);
    }
}
