package ru.kpfu.itis.lobanov.control2.utils;

public class CommandInfoProvider {
    public static final String START_COMMAND_NAME = "start";
    public static final String END_COMMAND_NAME = "end";
    public static final String INFO_COMMAND_NAME = "help";
    public static final String WEATHER_COMMAND_NAME = "weather";
    public static final String CHAT_COMMAND_NAME = "chat";
    public static final String EXCHANGE_RATE_COMMAND_NAME = "exchange";
    public static final String START_COMMAND_DESCRIPTION = "This command starts the bot";
    public static final String END_COMMAND_DESCRIPTION = "This command ends the bot";
    public static final String INFO_COMMAND_DESCRIPTION = "This command shows info about commands";
    public static final String WEATHER_COMMAND_DESCRIPTION = "This command shows weather info in the provided town";
    public static final String CHAT_COMMAND_DESCRIPTION = "This command starts the chat between several users";
    public static final String EXCHANGE_RATE_COMMAND_DESCRIPTION = "This command shows the exchange rate of the provided currency";

    private CommandInfoProvider() {
    }
}
