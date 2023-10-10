package ru.kpfu.itis.lobanov.homeworks.task3;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.lobanov.dto.UserDto;
import ru.kpfu.itis.lobanov.dto.WeatherDto;
import ru.kpfu.itis.lobanov.homeworks.task1.HttpClientImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.HashMap;

@WebServlet(urlPatterns = "/weather")
public class WeatherServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String townName = req.getParameter("town");
        HttpSession session = req.getSession();
        try {
            long startTime = System.nanoTime();

            resp.setContentType("text/plain");
            WeatherDto weatherDto = getWeatherAttributes(townName);
            resp.getWriter().write(String.format("Temperature: %s\nHumidity: %s\nPrecipitation: %s",
                    weatherDto.getTemperature(), weatherDto.getHumidity(), weatherDto.getPrecipitation()));

            long endTime = System.nanoTime();
            LOGGER.info(
                    "User with login {} logged in and searched for town {} in {} and result was given in {} millis",
                    session.getAttribute("userName"),
                    townName,
                    LocalDateTime.now(),
                    (int) ((endTime - startTime) / 1e6)
            );
        } catch (FileNotFoundException e) {
            req.getRequestDispatcher("task3/townNotFound.ftl").forward(req, resp);
        }
    }

    public static WeatherDto getWeatherAttributes(String townName) throws IOException {
        HttpClientImpl httpClient = new HttpClientImpl();
        String getResponse = httpClient.get(
                "https://api.openweathermap.org/data/2.5/weather?q=" + townName + "&appid=143c9d8999112b2f489a1e3a44de6ade",
                new HashMap<>()
        );
        JsonObject weatherJson = JsonParser.parseString(getResponse).getAsJsonObject();
        JsonObject currentWeather = weatherJson.get("main").getAsJsonObject();
        JsonElement temperature = currentWeather.get("temp");
        JsonElement humidity = currentWeather.get("humidity");
        JsonElement precipitation = weatherJson.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description");

        return new WeatherDto(
                new DecimalFormat("#0.00").format(Double.parseDouble(temperature.getAsString()) - 273) + " °C",
                humidity.getAsString() + "%",
                precipitation.getAsString()
        );
    }
}
