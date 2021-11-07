package io.cent.util;

import io.cent.DiscordBotCreator;

public class Log {
    public static void log(String msg) {
        System.out.println(msg);
        DiscordBotCreator.console.console.write(msg);
    }

    public static void warning(String msg) {
        System.out.println("[WARNING] " + msg);
        DiscordBotCreator.console.console.write("[WARNING] " + msg);
    }

    public static void error(String msg) {
        System.out.println("[ERROR] " + msg);
        DiscordBotCreator.console.console.write("[ERROR] " + msg);
    }

    public static void info(String msg) {
        System.out.println("[INFO] " + msg);
        DiscordBotCreator.console.console.write("[INFO] " + msg);
    }
}
