package io.cent.util;

import javax.swing.*;
import java.awt.*;

public class SMButton extends JButton {
    private static final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

    public SMButton(ImageIcon i, String tooltip) {
        super(i);
        setToolTipText(tooltip);
        setBorder(BorderFactory.createEmptyBorder());
        setContentAreaFilled(false);
        setCursor(cursor);
    }

    public SMButton(String i, String t) {
        this(DUtil.getIconImage(i), t);
    }

    public JToolTip createTT() {
        JToolTip tip = super.createToolTip();
        return tip;
    }
}
