package io.cent.window;

import io.cent.DiscordBotCreator;
import io.cent.assets.AssetObject;
import io.cent.data.LocalData;
import io.cent.listener.KeyListener;
import io.cent.project.Project;
import io.cent.project.ProjectCreator;
import io.cent.project.ProjectGetter;
import io.cent.util.DUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Locale;

public class MainWindow extends JFrame {
    private JMenuBar jMenuBar;
    private JMenu jMenu, help, projSubmenu1;
    private JMenuItem item, item3, project, settings, projectDetails;
    private JMenuItem helpItem1;
    private JMenuItem item2;
    private JLabel jLabel, projectLabel;

    // for project, create a tabbed pane
    private JTabbedPane tabbedPane;


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

        if (AssetObject.settings != null)
            settings = new JMenuItem("Settings", new ImageIcon(AssetObject.settings));
        else
            settings = new JMenuItem("Settings");
        settings.addActionListener(e -> {
            new SettingsWindow();
        });

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

            if (selected != null) {
                try {
                    Project project = ProjectGetter.get(selected);
                    project.switchTo();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Cannot open project!\n(Curropted or outdated)", "Open Project", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jMenu.add(item3);

        jMenu.add(settings);

        help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        help.add(item);
        helpItem1 = new JMenuItem("Webstie");
        help.add(helpItem1);

        jMenuBar.add(help);
        addKeyListener(new KeyListener());

        setJMenuBar(jMenuBar);

        setFocusable(true);
        setVisible(true);
        requestFocus();
    }

    public void setProject(Project project) {
        new Thread(() -> {
            try {
                jLabel.setText("Loading...");
                Thread.sleep(1000);
                jLabel.setText("");

                if (projectLabel != null) {
                    projectLabel.setText("Project: " + project.getName());

                    ActionListener curr = projectDetails.getActionListeners()[0];
                    projectDetails.removeActionListener(curr);

                    projectDetails.addActionListener(e -> new ProjectDetailsWindow(project));

                    projectLabel.setBounds(10, 10, 5000, 20);
                    projectLabel.setFont(new Font("Arial", Font.BOLD, 16));
                    return;
                }

                projectLabel = new JLabel("Project: " + project.getName());
                projectLabel.setBounds(10, 10, 5000, 20);
                projectLabel.setFont(new Font("Arial", Font.BOLD, 16));
                add(projectLabel);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Cannot open project!\n(Curropted or outdated)", "Open Project", JOptionPane.ERROR_MESSAGE);
            }

            projectDetails = new JMenuItem("Project Details");
            projectDetails.addActionListener(e -> {
                new ProjectDetailsWindow(project);
            });
            jMenu.add(projectDetails);
        }).start();
    }
}
