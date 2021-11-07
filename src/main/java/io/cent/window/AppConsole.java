package io.cent.window;

import io.cent.window.component.Console;
import io.cent.window.component.ConsoleBottomBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AppConsole extends JFrame {
    public Console console;
    private final ConsoleBottomBar bottomBar;
    private JPopupMenu contextmenu;

    private JMenuItem copy;

    public AppConsole() {
        super("AppConsole");
        setSize(650, 400);
        setMinimumSize(new Dimension(650, 400));

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());

        console = new Console();
        bottomBar = new ConsoleBottomBar();

        JScrollPane scroll = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.SOUTH);

        setupContextMenu();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component c = e.getComponent();
                int width = c.getWidth();
                int height = c.getHeight();
                console.setSize(width, height - bottomBar.getHeight());
                bottomBar.setSize(width, bottomBar.getHeight());
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                Component c = e.getComponent();
                int x = c.getX();
                int y = c.getY();
                console.setLocation(0, 0);
                bottomBar.setLocation(0, console.getHeight());
            }
        });
    }

    public void setupContextMenu() {
        contextmenu = new JPopupMenu();

        copy = new JMenuItem("Copy");
        copy.addActionListener(e -> {
            console.copy();
        });

        contextmenu.add(copy);
        console.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    contextmenu.show(console, e.getX(), e.getY());
                }
            }
        });
    }

    public String getLog() {
        return console.getText();
    }

    public void clearConsole() {
        console.setText("");
    }
}
