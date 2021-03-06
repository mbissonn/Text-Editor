package com.MEB.PortfolioProject;

import javax.swing.*;

public class Window extends JFrame {

    // INSTANCE VARIABLES

    private final JTextArea area = new JTextArea();
    private final JFrame frame;
    private final FileManager fileManager = new FileManager();
    private final FileMenu fileMenu = new FileMenu(area, fileManager);

    //CONSTRUCTOR

    public Window() {

        // JFRAME DEFAULT ATTRIBUTES

        frame = new JFrame("Text Edit");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(area);
        frame.setSize(960, 720);

        // MENU OPTIONS

        JMenuBar windowMenuBar = new JMenuBar();

        windowMenuBar.add(fileMenu.getMenu());

        frame.setJMenuBar(windowMenuBar);

        frame.setVisible(true);

    }

}
