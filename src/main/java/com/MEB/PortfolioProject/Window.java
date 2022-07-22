package com.MEB.PortfolioProject;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends JFrame {

    // INSTANCE VARIABLES

    private final JTextArea area = new JTextArea();
    private final JFrame frame = new JFrame("Text Edit");
    private final FileManager fileManager = new FileManager();
    private final FileMenu fileMenu;
    private final DialogueBuilder dialogueBuilder;
    private final WindowManager windowManager;

    private final Window window = this;

    //CONSTRUCTOR

    public Window(WindowManager windowManager) {

        // WINDOW MANAGEMENT

        this.windowManager = windowManager;
        windowManager.addWindow(this);
        this.fileMenu = new FileMenu(area, fileManager, this, windowManager);
        this.dialogueBuilder = fileMenu.getDialogueBuilder();

        // JFRAME DEFAULT ATTRIBUTES

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.add(area);
        frame.setSize(960, 720);

        // INITIALIZE CLOSING SAVE PROMPT

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dialogueBuilder.saveOnCloseDialogue(window, area);
            }
        });

        // MENU OPTIONS

        JMenuBar windowMenuBar = new JMenuBar();

        windowMenuBar.add(fileMenu.getMenu());

        frame.setJMenuBar(windowMenuBar);

        frame.setVisible(true);

    }

    // GETTERS

    public JFrame getFrame() {
        return frame;
    }

    public JTextArea getArea() {
        return area;
    }

    public FileMenu getFileMenu() {
        return fileMenu;
    }

}
