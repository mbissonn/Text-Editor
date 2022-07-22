package com.MEB.PortfolioProject;

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
        System.out.println(openWindows.size());
        while (openWindows.size() > 0) {
            for (int i = 0; i < openWindows.size(); i++) {
                openWindows.get(i).getFrame().toFront();
                openWindows.get(i).getFileMenu().getDialogueBuilder().saveOnCloseDialogue(openWindows.get(i), openWindows.get(i).getArea());
            }
        }
    }
}

