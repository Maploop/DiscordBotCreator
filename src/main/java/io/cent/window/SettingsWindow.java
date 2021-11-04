package io.cent.window;

import io.cent.assets.AssetObject;
import io.cent.data.SettingsJSON;
import io.cent.theme.Theme;
import io.cent.util.DUtil;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SettingsWindow extends JFrame {
    private JCheckBox askBeforeExit;
    private JButton save, openThemeFolder;
    private JComboBox<String> theme;
    private JLabel themeLabel;

    public SettingsWindow() {
        super("Settings");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        List<String> themes = new ArrayList<>();
        for (File f : DUtil.themes.listFiles()) {
            Theme theme = Theme.parse(f);
            themes.add(theme.getName());
        }

        themeLabel = new JLabel("Theme");
        themeLabel.setBounds(10, 10, 100, 20);
        add(themeLabel);

        theme = new JComboBox<>(themes.toArray(new String[0]));
        theme.setBounds(110, 10, 100, 20);
        theme.setSelectedItem(SettingsJSON.get("theme"));
        add(theme);

        save = new JButton("Save");
        save.setBounds(150, 120, 100, 30);
        save.addActionListener(e -> {
            SettingsJSON.set("askBeforeExit", askBeforeExit.isSelected());
            SettingsJSON.set("theme", theme.getSelectedItem().toString());
            dispose();
        });

        openThemeFolder = new JButton("Themes Folder");
        openThemeFolder.setBounds(10, 120, 130, 30);
        openThemeFolder.addActionListener(e -> {
            try {
                Runtime.getRuntime().exec("explorer.exe /select," + DUtil.themes.getAbsolutePath());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to open theme folder", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(openThemeFolder);

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
