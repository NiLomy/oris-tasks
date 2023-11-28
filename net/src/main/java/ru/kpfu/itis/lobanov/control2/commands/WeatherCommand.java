package ru.kpfu.itis.lobanov.control2.commands;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.control.TextArea;
import ru.kpfu.itis.lobanov.control2.BotApplication;
import ru.kpfu.itis.lobanov.control2.httpclient.HttpClientImpl;
import ru.kpfu.itis.lobanov.control2.utils.BotResponsesStringsProvider;
import ru.kpfu.itis.lobanov.control2.utils.CommandInfoProvider;
import ru.kpfu.itis.lobanov.control2.utils.InputParser;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeatherCommand implements Command {
    private final TextArea chat;

    public WeatherCommand(TextArea chat) {
        this.chat = chat;
    }

    @Override
    public void execute() {
        List<String> args = InputParser.getArgs();
        if (args != null && args.size() == 1) {
            String townName = args.get(0);

            try {
                List<String> weather = getWeatherAttributes(townName);
                chat.appendText(String.format(BotResponsesStringsProvider.WEATHER_COMMAND_RESPONSE, weather.get(0), weather.get(1), weather.get(2)));
            } catch (IOException e) {
                chat.appendText(BotResponsesStringsProvider.NET_EXCEPTION_MESSAGE);
            }
        }
    }

    private List<String> getWeatherAttributes(String townName) throws IOException {
        HttpClientImpl httpClient = new HttpClientImpl();
        String getResponse = httpClient.get(
                String.format(BotResponsesStringsProvider.WEATHER_URL, townName),
                new HashMap<>()
        );
        JsonObject weatherJson = JsonParser.parseString(getResponse).getAsJsonObject();
        JsonObject currentWeather = weatherJson.get(BotResponsesStringsProvider.WEATHER_MAIN_KEY).getAsJsonObject();
        JsonElement temperature = currentWeather.get(BotResponsesStringsProvider.WEATHER_TEMP_KEY);
        JsonElement humidity = currentWeather.get(BotResponsesStringsProvider.WEATHER_HUMIDITY_KEY);
        JsonElement precipitation = weatherJson.get(BotResponsesStringsProvider.WEATHER_WEATHER_KEY).getAsJsonArray().get(0).getAsJsonObject().get(BotResponsesStringsProvider.WEATHER_DESCRIPTION_KEY);

        List<String> weather = new ArrayList<>();
        weather.add(new DecimalFormat("#0.00").format(Double.parseDouble(temperature.getAsString()) - 273) + " °C");
        weather.add(humidity.getAsString() + "%");
        weather.add(precipitation.getAsString());
        return weather;
    }

    @Override
    public String getName() {
        return CommandInfoProvider.WEATHER_COMMAND_NAME;
    }

    @Override
    public String getDescription() {
        return CommandInfoProvider.WEATHER_COMMAND_DESCRIPTION;
    }
}
