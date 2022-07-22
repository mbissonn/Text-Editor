package com.MEB.PortfolioProject;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {
    public static void main(String[] args) {

        // LOOK AND FEEL

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }

        // CREATE WINDOW MANAGER

        WindowManager windowManager = new WindowManager();

        // CREATE WINDOW

        Window window = new Window(windowManager);
    }

}
