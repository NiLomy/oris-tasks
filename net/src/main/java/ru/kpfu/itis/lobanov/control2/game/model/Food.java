package ru.kpfu.itis.lobanov.control2.game.model;

import javafx.scene.shape.Rectangle;
import ru.kpfu.itis.lobanov.control2.game.utils.GameSettings;

public abstract class Food extends LocatableObject {
    public Food(double x, double y) {
        this.view = new Rectangle();
        setX(x);
        setY(y);
        view.setWidth(GameSettings.FOOD_SIZE);
        view.setHeight(GameSettings.FOOD_SIZE);
    }

    @Override
    public void setX(double x) {
        super.setX(x);
        this.view.setX(x);
    }

    @Override
    public void setY(double y) {
        super.setY(y);
        this.view.setY(y);
    }
}
