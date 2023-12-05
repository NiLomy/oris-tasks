package ru.kpfu.itis.lobanov.control2.game.model;

import javafx.scene.shape.Rectangle;

public abstract class LocatableObject {
    protected double x;
    protected double y;
    protected Rectangle view;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Rectangle getView() {
        return view;
    }

    public void setView(Rectangle view) {
        this.view = view;
    }
}
