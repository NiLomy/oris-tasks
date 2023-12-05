package ru.kpfu.itis.lobanov.control2.utils;

import javafx.scene.control.TextArea;
import ru.kpfu.itis.lobanov.control2.commands.*;

public class CommandRepository {
    public static Command[] getCommands(TextArea chat) {
        return new Command[]{
                new StartCommand(chat),
                new EndCommand(chat),
                new InfoCommand(chat),
                new WeatherCommand(chat),
                new ChatCommand(chat),
                new ExchangeRateCommand(chat),
                new SnakeCommand(chat)
        };
    }
}
