package io.cent.project;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class ProjectGetter {
    public static Project get(File path) throws Exception {
        JSONParser parser = new JSONParser();

        Object json = parser.parse(new FileReader(path + File.separator + "project.json"));
        JSONObject object = (JSONObject) json;

        return new Project(object.get("name").toString());
    }
}
