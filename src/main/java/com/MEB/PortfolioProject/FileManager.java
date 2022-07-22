package com.MEB.PortfolioProject;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class FileManager {

    // INSTANCE VARIABLES

    private File file;
    private final JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    //CONSTRUCTOR

    public FileManager() {
        jFileChooser.setDialogTitle("Choose destination.");
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    }

    // OPEN FILE

    public File openFile(JTextArea area) {
        String text = "";
        File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
        try {
            FileReader read = new FileReader(file);
            Scanner scan = new Scanner(read);
            while (scan.hasNextLine()) {
                String line = scan.nextLine() + "\n";
                text = text + line;
            }
            area.setText(text);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return file;
    }

    // SAVE FILE AS

    public File saveFileAs(JTextArea area) {
        try {
            File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
            FileWriter out = new FileWriter(file);
            out.write(area.getText());
            out.close();
            return file;
        } catch (FileNotFoundException ex) {
            Component f = null;
            JOptionPane.showMessageDialog(null, "File not found.");
        } catch (IOException ex) {
            Component f = null;
            JOptionPane.showMessageDialog(null, "Error.");
        } catch (Exception e) {
        }
        return null;
    }

    // SAVE FILE (NO DIALOGUE)

    public void saveFile(JTextArea area, File file) {
        try {
            FileWriter out = new FileWriter(file);
            out.write(area.getText());
            out.close();
        } catch (FileNotFoundException ex) {
            Component f = null;
            JOptionPane.showMessageDialog(f, "File not found.");
        } catch (IOException ex) {
            Component f = null;
            JOptionPane.showMessageDialog(f, "Error.");
        } catch (Exception e) {
        }
    }

    // MENU SELECTION RETURN VALUE HELPER METHOD

    public int setReturnValue(String input) {
        switch (input) {
            case "Save as":
                return jFileChooser.showSaveDialog(null);
            case "Open":
                return jFileChooser.showOpenDialog(null);
        }
        return 0;
    }

    // GETTERS

    public File getFile() {
        return file;
    }

    // SETTERS

    public void setFile(File file) {
        this.file = file;
    }

}
