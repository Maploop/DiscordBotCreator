package io.cent.window;

import io.cent.project.Project;

import javax.swing.*;

public class ProjectDetailsWindow extends JFrame {
    private JLabel projectNameLabel, projectDescriptionLabel, projectStartDateLabel, projectAuthorLabel;
    private JButton close;

    public ProjectDetailsWindow(Project project) {
        super("Project Details");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        projectNameLabel = new JLabel("Project Name: " + project.getName());
        projectNameLabel.setBounds(10, 10, 400, 20);
        add(projectNameLabel);
        projectDescriptionLabel = new JLabel("Project Description: " + project.getDescription());
        projectDescriptionLabel.setBounds(10, 40, 400, 20);
        add(projectDescriptionLabel);
        projectAuthorLabel = new JLabel("Project Author: " + project.getAuthor());
        projectAuthorLabel.setBounds(10, 70, 400, 20);
        add(projectAuthorLabel);
        projectStartDateLabel = new JLabel("Project Start Date: " + project.getStartDate());
        projectStartDateLabel.setBounds(10, 100, 400, 20);
        add(projectStartDateLabel);

        close = new JButton("Close");
        close.setBounds(10, 200, 100, 20);
        close.addActionListener(e -> dispose());
        add(close);

        setVisible(true);
    }
}
