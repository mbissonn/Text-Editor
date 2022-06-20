package com.MEB.PortfolioProject;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class TextEdit extends JFrame implements ActionListener {
    private JTextArea area;
    private JFrame frame;
    private int returnValue = 0;
    private File file;
    private JMenuItem menuItemNew = new JMenuItem("New");
    private JMenuItem menuItemOpen = new JMenuItem("Open");
    private JMenuItem menuItemSaveAs = new JMenuItem("Save as");
    private JMenuItem menuItemSave = new JMenuItem("Save");
    private JMenuItem menuItemQuit = new JMenuItem("Quit");

    public TextEdit() {
        this.run();
    }

    public void run() {
        frame = new JFrame("Text Edit");

        // Set look-and-feel (LNF)
        // Default to host system preference
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TextEdit.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set app window default attributes
        area = new JTextArea();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(area);
        frame.setSize(960, 720);
        frame.setVisible(true);

        // Build menu
        JMenuBar menu_main = new JMenuBar();

        JMenu menu_file = new JMenu("File");

        menuItemNew.addActionListener(this);
        menuItemOpen.addActionListener(this);
        menuItemSaveAs.addActionListener(this);
        menuItemSave.addActionListener(this);
        menuItemQuit.addActionListener(this);

        menu_main.add(menu_file);

        menu_file.add(menuItemNew);
        menu_file.add(menuItemOpen);
        menu_file.add(menuItemSaveAs);
        menu_file.add(menuItemSave);
        menu_file.add(menuItemQuit);

        menuItemSave.setEnabled(false);

        frame.setJMenuBar(menu_main);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ingest = null;
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose destination.");
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        // OPEN
        String ae = e.getActionCommand();
        if (ae.equals("Open")) {
            returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                file = new File(jfc.getSelectedFile().getAbsolutePath());
                if (!menuItemSave.isEnabled()) {
                    menuItemSave.setEnabled(true);
                }
                try {
                    FileReader read = new FileReader(file);
                    Scanner scan = new Scanner(read);
                    while (scan.hasNextLine()) {
                        String line = scan.nextLine() + "\n";
                        ingest = ingest + line;
                    }
                    area.setText(ingest);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            // SAVE AS
        } else if (ae.equals("Save as")) {
            returnValue = jfc.showSaveDialog(null);
            try {
                file = new File(jfc.getSelectedFile().getAbsolutePath());
                if (!menuItemSave.isEnabled()) {
                    menuItemSave.setEnabled(true);
                }
                FileWriter out = new FileWriter(file);
                out.write(area.getText());
                out.close();
            } catch (FileNotFoundException ex) {
                Component f = null;
                JOptionPane.showMessageDialog(f, "File not found.");
            } catch (IOException ex) {
                Component f = null;
                JOptionPane.showMessageDialog(f, "Error.");
            }
            // SAVE
        } else if (ae.equals("Save")) {
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
            }
            // NEW
        } else if (ae.equals("New")) {
            area.setText("");
            // QUIT
        } else if (ae.equals("Quit")) {
            System.exit(0);
        }
    }
}
