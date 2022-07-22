package com.MEB.PortfolioProject;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class FileMenu implements ActionListener {

    // INSTANCE VARIABLES

    private final FileManager fileManager;
    private final JMenu menu;
    private final Window window;
    private final JTextArea area;
    private final DialogueBuilder dialogueBuilder;
    private final WindowManager windowManager;
    private int returnValue = 0;

    private final JMenuItem menuItemNew = new JMenuItem("New Window");
    private final JMenuItem menuItemOpen = new JMenuItem("Open");
    private final JMenuItem menuItemSaveAs = new JMenuItem("Save as");
    private final JMenuItem menuItemSave = new JMenuItem("Save");
    private final JMenuItem menuItemClose = new JMenuItem("Close Window");
    private final JMenuItem menuItemQuit = new JMenuItem("Quit");

    private KeyStroke keyStrokeToNew = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
    private KeyStroke keyStrokeToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
    private KeyStroke keyStrokeToSaveAs = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK);
    private KeyStroke keyStrokeToSave = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
    private KeyStroke keyStrokeToClose = KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK);

    // CONSTRUCTOR

    public FileMenu(JTextArea area, FileManager fileManager, Window window, WindowManager windowManager) {

        // INITIALIZE INSTANCE VARIABLES

        this.windowManager = windowManager;
        this.window = window;
        this.area = window.getArea();
        this.fileManager = fileManager;
        this.menu = new JMenu("File");
        this.dialogueBuilder = new DialogueBuilder(this, fileManager, windowManager);

        // CREATE ACTION LISTENERS FOR MENU OPTIONS

        menuItemNew.addActionListener(this);
        menuItemOpen.addActionListener(this);
        menuItemSaveAs.addActionListener(this);
        menuItemSave.addActionListener(this);
        menuItemClose.addActionListener(this);
        menuItemQuit.addActionListener(this);

        // BIND MENU SHORTCUTS TO RESPECTIVE OPTIONS

        menuItemNew.setAccelerator(keyStrokeToNew);
        menuItemOpen.setAccelerator(keyStrokeToOpen);
        menuItemSaveAs.setAccelerator(keyStrokeToSaveAs);
        menuItemSave.setAccelerator(keyStrokeToSave);
        menuItemClose.setAccelerator(keyStrokeToClose);

        // ADD MENU OPTIONS TO MENU

        menu.add(menuItemNew);
        menu.add(menuItemOpen);
        menu.add(menuItemSaveAs);
        menu.add(menuItemSave);
        menu.add(menuItemClose);
        menu.add(menuItemQuit);

        // SAVE OFF BY DEFAULT

        menuItemSave.setEnabled(false);
    }

    // MENU SELECTION METHOD

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String thisActionEvent = actionEvent.getActionCommand();
        switch (thisActionEvent) {
            case "New Window":
                Window newWindow = new Window(windowManager);
                break;
            case "Open":
                open();
                break;
            case "Save as":
                saveAs();
                break;
            case "Save":
                fileManager.saveFile(area, fileManager.getFile());
                break;
            case "Close Window":
                dialogueBuilder.saveOnCloseDialogue(window, area);
                break;
            case "Quit":
                windowManager.closeAllWindows();
        }
    }

    // MENU SELECTION HELPER METHODS

    public void open() {
        returnValue = fileManager.setReturnValue("Open");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            fileManager.setFile(fileManager.openFile(area));
            window.setTitle(fileManager.getFile().getName());
            if (!menuItemSave.isEnabled()) {
                menuItemSave.setEnabled(true);
            }
        }
    }

    public void saveAs() {
        returnValue = fileManager.setReturnValue("Save as");
        fileManager.setFile(fileManager.saveFileAs(area));
        window.setTitle(fileManager.getFile().getName());
        if (!menuItemSave.isEnabled()) {
            menuItemSave.setEnabled(true);
        }
    }

    // GETTERS

    public JMenu getMenu() {
        return menu;
    }

    public JMenuItem getMenuItemSave() {
        return menuItemSave;
    }

    public DialogueBuilder getDialogueBuilder() {
        return dialogueBuilder;
    }
}


