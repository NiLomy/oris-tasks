package ru.kpfu.itis.lobanov.control2.utils;

public class BotResponsesStringsProvider {
    public static final String APP_NAME = "Info Bot";
    public static final String GAME_NAME = "SNAKE";
    public static final String HELLO_MESSAGE = "You successfully started the chat\n";
    public static final String GOODBYE_MESSAGE = "Goodbye!\n";
    public static final String USER_PRINT = "you: %s\n";
    public static final String EXCHANGE_RATE_RESPONSE = "Current value for 1%s = %s\n";
    public static final String WEATHER_COMMAND_RESPONSE = "Temperature: %s\nHumidity: %s\nPrecipitation: %s\n";
    public static final String INVALID_COMMAND_MESSAGE = "There is no such command. Please use 'help' for more information\n";
    public static final String START_CHAT_MESSAGE = "Firstly, you should start the chat\n";
    public static final String NET_EXCEPTION_MESSAGE = "Oooops, something went wrong with the net =(\n";
    public static final String CHAT_EXCEPTION_MESSAGE = "Oooops, something went wrong launching chat application =(";
    public static final String INVALID_ARGUMENTS_FOR_EXCHANGE_RATE_COMMAND = "You should write 2 parameters: initial currency and new currency\n";
    public static final String API_KEY = "b26cc75048e07976c20fb8a312bd6e6f";
    public static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=143c9d8999112b2f489a1e3a44de6ade";
    public static final String EXCHANGE_RATE_URL_1 = "http://api.currencylayer.com/live?access_key=";
    public static final String EXCHANGE_RATE_URL_2 = "&currencies=";
    public static final String EXCHANGE_RATE_URL_3 = "&source=";
    public static final String JSON_CURRENCY_KEY = "quotes";
    public static final String WEATHER_MAIN_KEY = "main";
    public static final String WEATHER_TEMP_KEY = "temp";
    public static final String WEATHER_HUMIDITY_KEY = "humidity";
    public static final String WEATHER_WEATHER_KEY = "weather";
    public static final String WEATHER_DESCRIPTION_KEY = "description";
    public static final String GAME_EXCEPTION_MESSAGE = "Oooops, something went wrong launching game application =(";


    private BotResponsesStringsProvider() {
    }

    ;
}
