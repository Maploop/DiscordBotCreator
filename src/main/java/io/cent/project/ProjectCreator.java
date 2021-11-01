package io.cent.project;

import io.cent.DiscordBotCreator;
import io.cent.util.DUtil;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.util.UUID;

public class ProjectCreator {
    private final String name, desc, author;

    public ProjectCreator(String name, String desc, String author) {
        this.name = name;
        this.desc = desc;
        this.author = author;
    }

    public Project create() {
        File projectFolder = new File(DUtil.projects + File.separator + name);
        projectFolder.mkdirs();

        JSONObject object = new JSONObject();
        object.put("creationDate", System.currentTimeMillis());
        object.put("name", name);
        object.put("description", desc);
        object.put("author", author);
        object.put("formatting", DiscordBotCreator.VERSION);
        object.put("encoding", "utf8");
        object.put("id", UUID.randomUUID().toString().replaceAll("-", ""));

        try (FileWriter fw = new FileWriter(projectFolder.getAbsolutePath() + File.separator + "project.json")) {
            fw.write(object.toJSONString());
            fw.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error creating project:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return new Project(name);
    }
}
