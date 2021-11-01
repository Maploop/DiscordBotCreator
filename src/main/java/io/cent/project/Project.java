package io.cent.project;

import io.cent.DiscordBotCreator;

public class Project {
    private final String name;

    public Project(String name) {
        this.name = name;
    }

    public void switchTo() {
        DiscordBotCreator.openProject = this;
        DiscordBotCreator.updateProject();
    }

    public String getName() {
        return name;
    }
}
