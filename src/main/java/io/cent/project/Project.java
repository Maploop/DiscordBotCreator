package io.cent.project;

import io.cent.DiscordBotCreator;
import io.cent.util.DUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class Project {
    private final String name;
    private String description, author, id, startDate;
    private final File projectJson;

    public Project(String name) {
        this.name = name;
        File file = new File(DUtil.projects + File.separator + name + File.separator + "project.json");
        this.projectJson = file;

        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(new FileReader(file));
            this.description = obj.get("description").toString();
            this.author = obj.get("author").toString();
            this.id = obj.get("id").toString();
            this.startDate = DUtil.convertToDate(Long.parseLong(obj.get("creationDate").toString()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getAuthor() {
        return author;
    }

    public String getId() {
        return id;
    }

    public File getProjectJson() {
        return projectJson;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void switchTo() {
        DiscordBotCreator.openProject = this;
        DiscordBotCreator.updateProject();
    }

    public String getName() {
        return name;
    }
}
