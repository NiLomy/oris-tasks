package ru.kpfu.itis.lobanov.fx.chat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.kpfu.itis.lobanov.fx.chat.client.ChatClient;
import ru.kpfu.itis.lobanov.fx.chat.model.UserConfig;
import ru.kpfu.itis.lobanov.fx.chat.view.BaseView;
import ru.kpfu.itis.lobanov.fx.chat.view.ChatView;
import ru.kpfu.itis.lobanov.fx.chat.view.UserConfigView;

import java.io.IOException;

public class ChatApplication extends Application {

    private UserConfig userConfig;
    private UserConfigView configView;
    private ChatView chatView;
    private BorderPane root;
    private ChatClient chatClient;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Chat");
        primaryStage.setOnCloseRequest(e -> {
            try {
                chatClient.getSocket().close();
                System.exit(0);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        BaseView.setChatApplication(this);

        configView = new UserConfigView();
        chatView = new ChatView();

        chatClient = new ChatClient(this);
        root = new BorderPane();
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        setView(configView);
    }

    public void appendMessage(String message) {
        chatView.appendMessage(message + "\n");
    }

    public void setView(BaseView view) {
        root.setCenter(view.getView());
    }

    public void startChat() {
        chatClient.start();
    }

    public UserConfig getUserConfig() {
        return userConfig;
    }

    public void setUserConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

    public ChatView getChatView() {
        return chatView;
    }

    public ChatClient getChatClient() {
        return chatClient;
    }

    public void setChatClient(ChatClient chatClient) {
        this.chatClient = chatClient;
    }
}
