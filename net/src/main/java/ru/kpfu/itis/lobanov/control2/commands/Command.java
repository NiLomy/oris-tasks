package ru.kpfu.itis.lobanov.control2.commands;

public interface Command {
    void execute();

    String getName();

    String getDescription();
}
