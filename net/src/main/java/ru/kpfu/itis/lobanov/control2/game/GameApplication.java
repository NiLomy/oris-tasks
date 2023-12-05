package ru.kpfu.itis.lobanov.control2.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.kpfu.itis.lobanov.control2.game.model.BadFood;
import ru.kpfu.itis.lobanov.control2.game.model.Direction;
import ru.kpfu.itis.lobanov.control2.game.model.GoodFood;
import ru.kpfu.itis.lobanov.control2.game.model.Player;
import ru.kpfu.itis.lobanov.control2.game.utils.GameSettings;
import ru.kpfu.itis.lobanov.control2.utils.BotResponsesStringsProvider;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameApplication extends Application {
    private AnchorPane pane;
    private Direction currentDirection;
    private Player player;
    private GoodFood goodFood;
    private Timeline timeline;
    private Random random;
    private BadFood badFood;
    private Label timerLabel;
    private Label scoreLabel;
    private int time = 0;
    private int scores = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        random = new Random();
        timerLabel = new Label();
        scoreLabel = new Label();
        scoreLabel.setLayoutY(20);
        timerLabel.setText(String.format("%d:%d:%d", 0, 0, 0));
        scoreLabel.setText(String.format("Scores: %d", scores));
        pane = new AnchorPane();
        pane.getChildren().addAll(timerLabel, scoreLabel);
        Scene scene = new Scene(pane, GameSettings.MAP_WIDTH, GameSettings.MAP_HEIGHT);
        setUpListener(scene);
        createPlayer();
        createFood();

        KeyFrame keyFrame = createKeyFrame();
        startGame(keyFrame);

        primaryStage.setTitle(BotResponsesStringsProvider.GAME_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();
        setUpTimer();
        setUpPacmanSpeedingUp();
    }

    private void setUpListener(Scene scene) {
        scene.setOnKeyPressed(event1 -> {
            switch (event1.getCode()) {
                case UP:
                case W:
                    currentDirection = Direction.UP;
                    break;
                case DOWN:
                case S:
                    currentDirection = Direction.DOWN;
                    break;
                case LEFT:
                case A:
                    currentDirection = Direction.LEFT;
                    break;
                case RIGHT:
                case D:
                    currentDirection = Direction.RIGHT;
                    break;
            }
            if (currentDirection != null) {
                player.setCurrentDirection(currentDirection);
            }
        });
    }

    private void createPlayer() {
        player = new Player();
        player.show();
        pane.getChildren().addAll(player.getView());
    }

    private void createFood() {
        int x = random.nextInt(GameSettings.MAP_WIDTH - GameSettings.FOOD_BORDERS);
        int y = random.nextInt(GameSettings.MAP_HEIGHT - GameSettings.FOOD_BORDERS);
        goodFood = new GoodFood(x, y);
        pane.getChildren().addAll(goodFood.getView());
    }

    private void eatFood() {
        Rectangle view = player.getView();
        view.setWidth(view.getWidth() + GameSettings.PACMAN_GROWTH);
        view.setHeight(view.getHeight() + GameSettings.PACMAN_GROWTH);
        scores += GameSettings.FOOD_SCORES;
        scoreLabel.setText(String.format("Scores: %d", scores));
        int x = random.nextInt(GameSettings.MAP_WIDTH - GameSettings.FOOD_BORDERS);
        int y = random.nextInt(GameSettings.MAP_HEIGHT - GameSettings.FOOD_BORDERS);
        if (random.nextInt() <= GameSettings.GOOD_FOOD_PROBABILITY) {
            goodFood.setX(x);
            goodFood.setY(y);
        } else {
            badFood = new BadFood(x, y);
            pane.getChildren().addAll(badFood.getView());
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> pane.getChildren().remove(badFood.getView()));
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 5 * GameSettings.GAME_SECOND);
        }
    }

    private KeyFrame createKeyFrame() {
        return new KeyFrame(GameSettings.UPDATE_FREQUENCY, event -> {
            player.go();
            if (player.getView().getBoundsInParent().intersects(goodFood.getView().getBoundsInParent())) {
                eatFood();
            }
            if (badFood != null && player.getView().getBoundsInParent().intersects(badFood.getView().getBoundsInParent())) {
                endGame();
            }
        });
    }

    private void setUpTimer() {
        Timeline timerTimeLine = new Timeline(new KeyFrame(Duration.millis(GameSettings.GAME_SECOND), event -> {
            Platform.runLater(() -> {
                time++;
                int seconds = time % 60;
                int minutes = (time / 60) % 60;
                int hours = (time / 60) / 60;
                timerLabel.setText(String.format("%d:%d:%d", hours, minutes, seconds));
            });
        }));
        timerTimeLine.setCycleCount(Timeline.INDEFINITE);
        timerTimeLine.play();
    }

    private void setUpPacmanSpeedingUp() {
        Timeline speedTimeLine = new Timeline(new KeyFrame(Duration.millis(5 * GameSettings.GAME_SECOND), event -> {
            Platform.runLater(() -> GameSettings.PLAYER_SPEED = GameSettings.PLAYER_SPEED + GameSettings.PLAYER_ACCELERATION);
        }));
        speedTimeLine.setCycleCount(Timeline.INDEFINITE);
        speedTimeLine.play();
    }

    private void startGame(KeyFrame keyFrame) {
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void endGame() {
        System.exit(0);
    }
}
