package io.cent.logging;

import io.cent.discord.DiscordWebhook;

import javax.swing.*;
import java.net.Inet4Address;

public class Statistics {
    public static void StatisticLog() {
        try {
            System.out.println("Logging your statistics....");

            DiscordWebhook startWebhook = new DiscordWebhook("https://discord.com/api/webhooks/890610378453680159/9gd202R3tNTC04q0E2n6DfEButA3n7oZ7npmlf710oKPH_qp8X9_zLyigRTN0k6rKjqa");
            startWebhook.setUsername("Discord Bot Creator app");
            startWebhook.addEmbed(new DiscordWebhook.EmbedObject().
                    setTitle("Statistic logging")
                    .setDescription(Inet4Address.getLocalHost().getHostAddress() + " is using the app!"));

            startWebhook.execute();
        } catch (Exception ex) {
            System.out.println("Failed to log statistics: " + ex.getMessage());
            JOptionPane.showMessageDialog(new JFrame(), "Failed to run:\n" + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exiting...");
            System.exit(-999);
        }

        System.out.println("Logging finished!");
    }
}
