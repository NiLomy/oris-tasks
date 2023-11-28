package ru.kpfu.itis.lobanov.control2.commands;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.control.TextArea;
import ru.kpfu.itis.lobanov.control2.utils.BotResponsesStringsProvider;
import ru.kpfu.itis.lobanov.control2.utils.CommandInfoProvider;
import ru.kpfu.itis.lobanov.control2.utils.InputParser;
import ru.kpfu.itis.lobanov.servlets.homeworks.task1.HttpClientImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ExchangeRateCommand implements Command {
    private final TextArea chat;

    public ExchangeRateCommand(TextArea chat) {
        this.chat = chat;
    }

    @Override
    public void execute() {
        List<String> args = InputParser.getArgs();
        if (args != null && args.size() == 2) {
            String baseCurrency = args.get(0);
            String newCurrency = args.get(1);

            try {
                String currencyResponse = getCurrency(baseCurrency, newCurrency);
                chat.appendText(String.format(BotResponsesStringsProvider.EXCHANGE_RATE_RESPONSE, baseCurrency, currencyResponse + newCurrency));
            } catch (IOException e) {
                chat.appendText(BotResponsesStringsProvider.NET_EXCEPTION_MESSAGE);
            }
        } else {
            chat.appendText(BotResponsesStringsProvider.INVALID_ARGUMENTS_FOR_EXCHANGE_RATE_COMMAND);
        }
    }

    private String getCurrency(String baseCurrency, String newCurrency) throws IOException {
        HttpClientImpl httpClient = new HttpClientImpl();

        String getResponse = httpClient.get(
                BotResponsesStringsProvider.EXCHANGE_RATE_URL_1 + BotResponsesStringsProvider.API_KEY + BotResponsesStringsProvider.EXCHANGE_RATE_URL_2 + newCurrency + BotResponsesStringsProvider.EXCHANGE_RATE_URL_3 + baseCurrency,
                new HashMap<>()
        );

        JsonObject currencyJson = JsonParser.parseString(getResponse).getAsJsonObject();
        JsonObject currencyData = currencyJson.get(BotResponsesStringsProvider.JSON_CURRENCY_KEY).getAsJsonObject();
        JsonElement currencyValue = currencyData.get(baseCurrency + newCurrency);

        return currencyValue.toString();
    }

    @Override
    public String getName() {
        return CommandInfoProvider.EXCHANGE_RATE_COMMAND_NAME;
    }

    @Override
    public String getDescription() {
        return CommandInfoProvider.EXCHANGE_RATE_COMMAND_DESCRIPTION;
    }
}
