package io.cent.window.component;

import javax.swing.*;

public abstract class BottomBar extends JPanel {
    public BottomBar() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }
}
