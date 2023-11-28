package ru.kpfu.itis.lobanov.fx.chat.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ru.kpfu.itis.lobanov.fx.chat.model.UserConfig;

public class UserConfigView extends BaseView {

    private AnchorPane pane;
    private VBox box;
    private TextField host;
    private TextField port;
    private TextField username;
    private Button start;

    private final EventHandler<ActionEvent> startEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == start) {
                UserConfig userConfig = new UserConfig();
                userConfig.setHost(host.getText());
                userConfig.setPort(Integer.parseInt(port.getText()));
                userConfig.setUsername(username.getText());

                getChatApplication().setUserConfig(userConfig);

                getChatApplication().startChat();

                getChatApplication().setView(getChatApplication().getChatView());
            }
        }
    };

    @Override
    public Parent getView() {
        if (pane == null) {
            createView();
        }
        return pane;
    }

    private void createView() {
        pane = new AnchorPane();

        box = new VBox(10);

        Label hostLabel = new Label("host");
        Label portLabel = new Label("port");
        Label usernameLabel = new Label("username");

        host = new TextField();
        host.setText("127.0.0.1");
        port = new TextField();
        port.setText("5555");
        username = new TextField();
        start = new Button("Start chat");
        start.setOnAction(startEvent);

        box.getChildren().addAll(hostLabel, host, portLabel, port, usernameLabel, username, start);
        pane.getChildren().addAll(box);
    }
}
