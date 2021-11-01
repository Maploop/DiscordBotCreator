package io.cent.window;

import io.cent.assets.AssetObject;
import io.cent.data.LocalData;
import io.cent.project.Project;
import io.cent.project.ProjectCreator;
import io.cent.util.DUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Locale;

public class MainWindow extends JFrame {
    private JMenuBar jMenuBar;
    private JMenu jMenu, help, projSubmenu1;
    private JMenuItem item, item3, project, settings;
    private JMenuItem helpItem1;
    private JMenuItem item2;
    private JLabel jLabel;

    public MainWindow() {
        super("Discord Bot Creator by Cent");

        jMenuBar = new JMenuBar();
        Font font = new Font("myfont", Font.BOLD, 25);
        jLabel = new JLabel("Welcome to Discord Bot Creator!", SwingConstants.CENTER);
        jLabel.setFont(font);

        add(jLabel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setIconImage(AssetObject.icon);

        jMenu = new JMenu("File");
        jMenu.setMnemonic(KeyEvent.VK_A);
        jMenu.getAccessibleContext().setAccessibleDescription("View information and create new projects");
        jMenuBar.add(jMenu);

        projSubmenu1 = new JMenu("New");
        project = new JMenuItem("Project");
        project.addActionListener(e -> {
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
        item2 = new JMenuItem("Module");
        item2.addActionListener(e -> {
            String moduleName = JOptionPane.showInputDialog(this, "Enter Module Name", "New Module", JOptionPane.QUESTION_MESSAGE);
            if (moduleName == null)
                return;
            String moduleDescription = JOptionPane.showInputDialog(this, "Enter Module Description", "New Module", JOptionPane.QUESTION_MESSAGE);
            String moduleAuthor = JOptionPane.showInputDialog(this, "Enter Module Author", "New Module", JOptionPane.QUESTION_MESSAGE);

            JOptionPane.showMessageDialog(this, "Module Created Successfully!\nName: " + moduleName +
                    "\nDescription: " + moduleDescription + "\nAuthor: " + moduleAuthor, "New Module", JOptionPane.INFORMATION_MESSAGE);
        });

        settings = new JMenuItem("Settings", new ImageIcon(AssetObject.settings));

        projSubmenu1.add(project);
        projSubmenu1.add(item2);

        item = new JMenuItem("About");
        item.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Discord Bot Creator by Cent\n\n" + "Version: " + LocalData.get("version") + "\n" +
                    "License Key: " + LocalData.get("licenseKey") + "\n" +
                    "Installation Time: " + DUtil.convertToDate(Long.parseLong(LocalData.get("installationTime").toLowerCase())), "About", JOptionPane.INFORMATION_MESSAGE);
        });

        jMenu.add(projSubmenu1);

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

        jMenu.add(settings);

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
