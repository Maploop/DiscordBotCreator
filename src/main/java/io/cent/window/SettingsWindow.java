package io.cent.window;

import io.cent.assets.AssetObject;
import io.cent.data.SettingsJSON;

import javax.swing.*;

public class SettingsWindow extends JFrame {
    private JCheckBox askBeforeExit;
    private JButton save;
    private JComboBox<String> theme;
    private JLabel themeLabel;

    public SettingsWindow() {
        super("Settings");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        themeLabel = new JLabel("Theme");
        themeLabel.setBounds(10, 10, 100, 20);
        add(themeLabel);

        theme = new JComboBox<>(new String[]{"MonoLight"});
        theme.setBounds(110, 10, 100, 20);
        add(theme);

        save = new JButton("Save");
        save.setBounds(150, 120, 100, 30);
        save.addActionListener(e -> {
            SettingsJSON.set("askBeforeExit", askBeforeExit.isSelected());
            SettingsJSON.set("theme", theme.getSelectedItem().toString());
            dispose();
        });

        askBeforeExit = new JCheckBox("Ask before exit");

        if (Boolean.parseBoolean(SettingsJSON.get("askBeforeExit")))
            askBeforeExit.setSelected(true);

        askBeforeExit.setBounds(10, 40, 200, 20);
        add(askBeforeExit);
        add(save);

        setIconImage(AssetObject.settings);

        setVisible(true);
    }
}
