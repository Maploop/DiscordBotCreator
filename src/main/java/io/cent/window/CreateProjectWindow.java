package io.cent.window;

import io.cent.project.Project;
import io.cent.project.ProjectCreator;

import javax.swing.*;

public class CreateProjectWindow extends JFrame {
    private JPanel contentPane;
    private JButton buttonCreate;
    private JButton buttonCancel;
    private JTextField textFieldProjectName;
    private JTextField textFieldProjectDesc;
    private JTextField textFieldProjectAuthor;
    private JTextField textFieldProjectVersion;

    private JLabel labelProjectName;
    private JLabel labelProjectDesc;
    private JLabel labelProjectAuthor;
    private JLabel labelProjectVersion;

    public CreateProjectWindow() {
        contentPane = new JPanel();

        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setSize(400, 300);
        setLocationRelativeTo(null);

        setTitle("Create new project");
        this.setLayout(null);

        buttonCreate = new JButton("Create");
        buttonCancel = new JButton("Cancel");
        textFieldProjectName = new JTextField();
        textFieldProjectDesc = new JTextField();
        textFieldProjectAuthor = new JTextField();
        textFieldProjectVersion = new JTextField();

        labelProjectName = new JLabel("Project Name");
        labelProjectDesc = new JLabel("Project Description");
        labelProjectAuthor = new JLabel("Project Author");
        labelProjectVersion = new JLabel("Project Version");

        textFieldProjectName.setColumns(10);
        textFieldProjectDesc.setColumns(10);
        textFieldProjectAuthor.setColumns(10);
        textFieldProjectVersion.setColumns(10);

        labelProjectName.setBounds(10, 10, 100, 20);
        labelProjectDesc.setBounds(10, 40, 120, 20);
        labelProjectAuthor.setBounds(10, 70, 100, 20);

        textFieldProjectName.setBounds(140, 10, 200, 20);
        textFieldProjectDesc.setBounds(140, 40, 200, 20);
        textFieldProjectAuthor.setBounds(140, 70, 200, 20);

        buttonCreate.setBounds(10, 100, 100, 20);
        buttonCancel.setBounds(120, 100, 100, 20);

        this.add(labelProjectName);
        this.add(labelProjectDesc);
        this.add(labelProjectAuthor);
        this.add(labelProjectVersion);
        this.add(textFieldProjectName);
        this.add(textFieldProjectDesc);
        this.add(textFieldProjectAuthor);
        this.add(textFieldProjectVersion);
        this.add(buttonCreate);
        this.add(buttonCancel);

        buttonCancel.addActionListener(e -> dispose());
        buttonCreate.addActionListener(e -> {
            String name = textFieldProjectName.getText();
            String desc = textFieldProjectDesc.getText();
            String author = textFieldProjectAuthor.getText();

            if (name == null || name.isEmpty() || desc == null || desc.isEmpty() || author == null || author.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Project project = new ProjectCreator(name, desc, author).create();

            dispose();

            int i = JOptionPane.showConfirmDialog(this, "Your project was created!\nOpen now?", "Open project", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                project.switchTo();
            }
        });

        setVisible(true);
    }
}
