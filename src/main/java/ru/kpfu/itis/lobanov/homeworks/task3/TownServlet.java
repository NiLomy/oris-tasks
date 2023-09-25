package ru.kpfu.itis.lobanov.homeworks.task3;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.kpfu.itis.lobanov.homeworks.task1.HttpClientImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "townServlet", urlPatterns = "/town")
public class TownServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        Object isAuthorized = httpSession.getAttribute("isAuthorized");
        if (isAuthorized != null && (boolean) isAuthorized) {
            req.getRequestDispatcher("task3/town.ftl").forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String townName = req.getParameter("town");

        Map<String, String> weatherAttributes = getWeatherAttributes(townName);
        req.setAttribute("townName", townName);
        weatherAttributes.forEach(req::setAttribute);
        req.getRequestDispatcher("task3/weather.ftl").forward(req, resp);
    }

    public static Map<String, String> getWeatherAttributes(String townName) throws IOException {
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

        Map<String, String> weatherAttributes = new HashMap<>();
        weatherAttributes.put("temperature", new DecimalFormat("#0.00").format(Double.parseDouble(temperature.getAsString()) - 273) + " °C");
        weatherAttributes.put("humidity", humidity.getAsString() + "%");
        weatherAttributes.put("precipitation", precipitation.getAsString());
        return weatherAttributes;
    }
}
