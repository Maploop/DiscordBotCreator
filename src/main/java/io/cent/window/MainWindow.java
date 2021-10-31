package io.cent.window;

import io.cent.assets.AssetObject;
import io.cent.data.LocalData;
import io.cent.project.Project;
import io.cent.project.ProjectCreator;
import io.cent.util.DUtil;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Locale;

public class MainWindow extends JFrame {
    private JMenuBar jMenuBar;
    private JMenu jMenu, help;
    private JMenuItem item, item3;
    private JMenuItem helpItem1;
    private JMenuItem item2;

    public MainWindow() {
        super("Discord Bot Creator by Cent");

        jMenuBar = new JMenuBar();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setIconImage(AssetObject.icon);

        jMenu = new JMenu("File");
        jMenu.setMnemonic(KeyEvent.VK_A);
        jMenu.getAccessibleContext().setAccessibleDescription("View information and create new projects");
        jMenuBar.add(jMenu);

        item = new JMenuItem("About");
        item.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Discord Bot Creator by Cent\n\n" + "Version: " + LocalData.get("version") + "\n" +
                    "License Key: " + LocalData.get("licenseKey") + "\n" +
                    "Installation Time: " + DUtil.convertToDate(Long.parseLong(LocalData.get("installationTime").toLowerCase())), "About", JOptionPane.INFORMATION_MESSAGE);
        });

        item2 = new JMenuItem("New Project");
        item2.addActionListener(e -> {
            String projectName = JOptionPane.showInputDialog(this, "Enter Project Name", "New Project", JOptionPane.QUESTION_MESSAGE);
            if (projectName == null)
                return;
            String projectDescription = JOptionPane.showInputDialog(this, "Enter Project Description", "New Project", JOptionPane.QUESTION_MESSAGE);
            String projectAuthor = JOptionPane.showInputDialog(this, "Enter Project Author", "New Project", JOptionPane.QUESTION_MESSAGE);

            ProjectCreator pc = new ProjectCreator(projectName, projectDescription, projectAuthor);
            Project project = pc.create();
            if (project != null) {
                JOptionPane.showMessageDialog(this, "Project Created Successfully!", "New Project", JOptionPane.INFORMATION_MESSAGE);
                project.switchTo();
            }
        });
        jMenu.add(item2);

        item3 = new JMenuItem("Open");
        item3.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setCurrentDirectory(DUtil.projects);
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int retval = jfc.showOpenDialog(this);
            File selected = jfc.getSelectedFile();
            System.out.println(selected.toString());

            JOptionPane.showMessageDialog(this, "Cannot open project!\n(Curropted or outdated)", "Open Project", JOptionPane.ERROR_MESSAGE);
        });
        jMenu.add(item3);

        help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        help.add(item);
        helpItem1 = new JMenuItem("Webstie");
        help.add(helpItem1);

        jMenuBar.add(help);

        setJMenuBar(jMenuBar);

        setVisible(true);
    }
}
