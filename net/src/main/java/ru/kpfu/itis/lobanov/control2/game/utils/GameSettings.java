package ru.kpfu.itis.lobanov.control2.game.utils;

import javafx.util.Duration;

public class GameSettings {
    public static final int MAP_WIDTH = 400;
    public static final int MAP_HEIGHT = 400;
    public static final int PLAYER_SIZE = 20;
    public static final int FOOD_SIZE = 15;
    public static int PLAYER_SPEED = 10;
    public static final int PLAYER_ACCELERATION = 2;
    public static final Duration UPDATE_FREQUENCY = Duration.millis(200);
    public static final int GAME_SECOND = 1000;
    public static final int PACMAN_GROWTH = 5;
    public static final int FOOD_BORDERS = 50;
    public static final int FOOD_SCORES = 25;
    public static final double GOOD_FOOD_PROBABILITY = 0.7;

    private GameSettings() {
    }
}
