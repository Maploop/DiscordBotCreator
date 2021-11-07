package io.cent.window.component;

import io.cent.DiscordBotCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ConsoleBottomBar extends BottomBar {
    private final JButton clear = new JButton("Clear");
    private final JButton copy = new JButton("Copy");
    private final JButton upload = new JButton("Upload");
    private final JButton kill = new JButton("Kill");

    public ConsoleBottomBar() {
        this.addActionListeners();

        JPanel leftSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 13));
        leftSide.add(clear);
        leftSide.add(copy);
        leftSide.add(upload);
        leftSide.add(kill);

        this.kill.setEnabled(false);
        this.add(leftSide, BorderLayout.WEST);

    }

    private void addActionListeners() {
        copy.addActionListener(e -> {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection selec = new StringSelection(DiscordBotCreator.console.console.getText());
            clipboard.setContents(selec, null);

            DiscordBotCreator.pop("Copied!");
        });

        clear.addActionListener(e -> DiscordBotCreator.console.console.setText(""));
    }
}
