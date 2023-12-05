package ru.kpfu.itis.lobanov.control2.game.model;

import javafx.scene.paint.Color;

public class GoodFood extends Food {
    public GoodFood(double x, double y) {
        super(x, y);
        this.view.setFill(Color.BLACK);
    }
}
