package com.MEB.PortfolioProject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WindowManager {

    private List<Window> openWindows = new ArrayList<>();

    public void addWindow(Window window) {
        openWindows.add(window);
    }

    public void removeWindow(Window window) {
        openWindows.remove(window);
    }

    public void closeAllWindows() {
        while (openWindows.size() > 0) {
            for (int i = 0; i < openWindows.size(); i++) {
                openWindows.get(i).getFrame().toFront();
                openWindows.get(i).getFileMenu().closeWindow();
            }
        }
    }

    public Point newWindowLocation(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int modifier = openWindows.size() % 14;
        return new Point(1 / screenSize.width + modifier * screenSize.width / 50, 1 / screenSize.height + modifier * screenSize.height / 50);
    }

}

