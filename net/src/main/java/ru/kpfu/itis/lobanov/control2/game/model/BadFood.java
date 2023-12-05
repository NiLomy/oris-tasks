package ru.kpfu.itis.lobanov.control2.game.model;

import javafx.scene.paint.Color;

public class BadFood extends Food {
    public BadFood(double x, double y) {
        super(x, y);
        this.view.setFill(Color.YELLOW);
    }
}
