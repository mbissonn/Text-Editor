package com.MEB.PortfolioProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FileMenu implements ActionListener {

    // INSTANCE VARIABLES

    private final FileManager fileManager;
    private final JTextArea area;
    private final JMenu menu;
    private final JMenuItem menuItemNew = new JMenuItem("New Window");
    private final JMenuItem menuItemOpen = new JMenuItem("Open");
    private final JMenuItem menuItemSaveAs = new JMenuItem("Save as");
    private final JMenuItem menuItemSave = new JMenuItem("Save");
    private final JMenuItem menuItemQuit = new JMenuItem("Quit");

    private File file;
    private int returnValue = 0;

    // CONSTRUCTOR

    public FileMenu(JTextArea area, FileManager fileManager) {

        this.fileManager = fileManager;
        this.area = area;

        this.menu = new JMenu("File");

        menuItemNew.addActionListener(this);
        menuItemOpen.addActionListener(this);
        menuItemSaveAs.addActionListener(this);
        menuItemSave.addActionListener(this);
        menuItemQuit.addActionListener(this);

        menu.add(menuItemNew);
        menu.add(menuItemOpen);
        menu.add(menuItemSaveAs);
        menu.add(menuItemSave);
        menu.add(menuItemQuit);

        menuItemSave.setEnabled(false);
    }

    // MENU SELECTION ACTIONS

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // OPEN
        String thisActionEvent = actionEvent.getActionCommand();
        switch (thisActionEvent) {
            case "Open":
                returnValue = fileManager.setReturnValue("Open");
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    file = fileManager.openFile(area);
                    if (!menuItemSave.isEnabled()) {
                        menuItemSave.setEnabled(true);
                    }
                }
                break;
            // SAVE AS
            case "Save as":
                returnValue = fileManager.setReturnValue("Save as");
                file = fileManager.saveFileAs(area);
                if (!menuItemSave.isEnabled()) {
                    menuItemSave.setEnabled(true);
                }
                break;
            // SAVE
            case "Save":
                fileManager.saveFile(area, file);
                break;
            // NEW
            case "New Window":
                Window newWindow = new Window();
                break;
            // QUIT
            case "Quit":
                System.exit(0);
        }
    }

    // GETTERS

    public JMenu getMenu() {
        return menu;
    }
}
