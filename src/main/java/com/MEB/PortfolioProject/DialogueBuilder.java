package com.MEB.PortfolioProject;

import javax.swing.*;

public class DialogueBuilder {

    private FileMenu fileMenu;
    private FileManager fileManager;
    private WindowManager windowManager;

    public DialogueBuilder(FileMenu fileMenu, FileManager fileManager, WindowManager windowManager) {
        this.fileMenu = fileMenu;
        this.fileManager = fileManager;
        this.windowManager = windowManager;
    }

    public void saveOnCloseDialogue(Window window, JTextArea area) {
        String ObjButtons[] = {"Save", "Don't Save", "Cancel"};
        int promptResult = JOptionPane.showOptionDialog(null, "Would you like to save before quitting?", "Text Editor", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[0]);
        switch (promptResult) {
            case 0:
                if (fileMenu.getMenuItemSave().isEnabled()) {
                    fileManager.saveFile(area, fileManager.getFile());
                } else {
                    fileMenu.saveAs();
                }
            case 1:
                windowManager.removeWindow(window);
                window.getFrame().dispose();
        }
    }
}

