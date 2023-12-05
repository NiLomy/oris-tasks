package ru.kpfu.itis.lobanov.control2.game.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.kpfu.itis.lobanov.control2.game.utils.GameSettings;

public class Player {
    private double x;
    private double y;
    private Direction currentDirection;
    protected Rectangle view;

    public Player() {
        this.view = new Rectangle();

        setSpawnPoint();
    }

    private void setSpawnPoint() {
        this.x = GameSettings.MAP_WIDTH / 2;
        this.y = GameSettings.MAP_WIDTH / 2;
    }

    public void show() {
        view.setX(x);
        view.setY(y);
        view.setWidth(GameSettings.PLAYER_SIZE);
        view.setHeight(GameSettings.PLAYER_SIZE);
        view.setFill(Color.RED);
    }

    public void go() {
        Direction movingDirection = currentDirection;

        if (movingDirection != null) {
            switch (movingDirection) {
                case UP:
                    setY(y - GameSettings.PLAYER_SPEED);
                    if (y <= 0) {
                        setY(GameSettings.MAP_HEIGHT - GameSettings.PLAYER_SPEED);
                    }
                    break;
                case DOWN:
                    setY(y + GameSettings.PLAYER_SPEED);
                    if (y >= GameSettings.MAP_HEIGHT) {
                        setY(0);
                    }
                    break;
                case LEFT:
                    setX(x - GameSettings.PLAYER_SPEED);
                    if (x <= 0) {
                        setX(GameSettings.MAP_WIDTH);
                    }
                    break;
                case RIGHT:
                    setX(x + GameSettings.PLAYER_SPEED);
                    if (x >= GameSettings.MAP_WIDTH) {
                        setX(0);
                    }
                    break;
            }
        }
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        this.view.setX(x);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        this.view.setY(y);
    }

    public Rectangle getView() {
        return view;
    }

    public void setView(Rectangle view) {
        this.view = view;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }
}
