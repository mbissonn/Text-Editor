package com.MEB.PortfolioProject;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends JFrame {

    // INSTANCE VARIABLES

    private final JTextArea area = new JTextArea();
    private final JFrame frame = new JFrame("Text Edit");
    private final FileManager fileManager = new FileManager();
    private final FileMenu fileMenu;
    private final DialogueBuilder dialogueBuilder;

    private final Window window = this;

    private boolean changed = false;

    //CONSTRUCTOR

    public Window(WindowManager windowManager) {

        // WINDOW MANAGEMENT

        windowManager.addWindow(this);
        this.fileMenu = new FileMenu(area, fileManager, this, windowManager);
        this.dialogueBuilder = fileMenu.getDialogueBuilder();

        // ADD JTEXTAREA TO JFRAME, SIZE, AND ADD DOC LISTENER

        frame.add(area);
        frame.setSize(960, 720);

        area.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!changed) {
                    changed = true;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!changed) {
                    changed = true;
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!changed) {
                    changed = true;
                }
            }
        });

        // INITIALIZE CLOSING OPERATION

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (window.isChanged()) {
                    dialogueBuilder.saveOnCloseDialogue(window, area);
                } else {
                    windowManager.removeWindow(window);
                    window.getFrame().dispose();
                }
            }
        });

        // ADD MENU TO JFRAME

        JMenuBar windowMenuBar = new JMenuBar();

        windowMenuBar.add(fileMenu.getMenu());

        frame.setJMenuBar(windowMenuBar);

        // SET JFRAME LOCATION AND MAKE VISIBLE

        frame.setLocation(windowManager.newWindowLocation(frame));

        frame.setVisible(true);

    }

    // GETTERS

    public JFrame getFrame() {
        return frame;
    }

    public boolean isChanged() {
        return changed;
    }

    public JTextArea getArea() {
        return area;
    }

    public FileMenu getFileMenu() {
        return fileMenu;
    }

    // SETTERS

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
