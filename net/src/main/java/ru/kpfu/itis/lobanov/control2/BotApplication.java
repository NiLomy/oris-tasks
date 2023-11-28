package ru.kpfu.itis.lobanov.control2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.kpfu.itis.lobanov.control2.utils.BotResponsesStringsProvider;

import java.io.PrintWriter;

public class BotApplication extends Application {
    private static Stage primaryStage;
    private static final String initXML = "/bot.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        BotApplication.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(BotApplication.class.getResource(initXML));

        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);

        primaryStage.setTitle(BotResponsesStringsProvider.APP_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
