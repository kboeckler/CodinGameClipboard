package com.github.kboeckler.codingameclipboard;

import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SimpleCopyToClipboardGui {

  public static final String DEFAULT_IMPORTS =
      "import java.util.*;\nimport java.io.*;\nimport java.math.*;\nimport java.util.stream.*;\nimport java.util.function.*;";
  private final ClipboardManager manager;

  private JFrame mainFrame;
  private JTextField topComponent;
  private JTextArea middleComponent;
  private JCheckBox minifyCheckbox;
  private JPanel middleBottomPanel;
  private JButton bottomComponent;
  private JCheckBox packageCheckbox;

  public SimpleCopyToClipboardGui(ClipboardManager manager) {
    this.manager = manager;

    createMainFrame();
    setupListener();
  }

  private void createMainFrame() {
    createFrame();
    fillFrame();
    validateFrame();
  }

  private void validateFrame() {
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);
  }

  private void setupListener() {
    bottomComponent.addActionListener(
        e -> {
          String sourceFolder = topComponent.getText();
          if (manager.setSourceFolder(sourceFolder)) {
            manager.setImports(middleComponent.getText());
            manager.setShallMinify(minifyCheckbox.isSelected());
            manager.setShallRemovePackageDeclaration(packageCheckbox.isSelected());
            manager.executeMergeToClipboard();
          } else {
            JOptionPane.showMessageDialog(mainFrame, "Source Folder could not be found");
          }
        });
    middleComponent.addComponentListener(
        new ComponentListener() {

          @Override
          public void componentShown(ComponentEvent e) {}

          @Override
          public void componentResized(ComponentEvent e) {
            mainFrame.pack();
          }

          @Override
          public void componentMoved(ComponentEvent e) {}

          @Override
          public void componentHidden(ComponentEvent e) {}
        });
  }

  private void createFrame() {
    mainFrame = new JFrame("CodinGameClipboard");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame
        .getContentPane()
        .setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
    mainFrame.setResizable(false);
  }

  private void fillFrame() {
    createComponents();
    setupComponents();
    addComponents();
  }

  private void setupComponents() {
    middleBottomPanel.setLayout(new GridLayout(2, 2));
  }

  private void createComponents() {
    topComponent = new JTextField();
    topComponent.setText(".");
    middleComponent = new JTextArea();
    middleComponent.setText(DEFAULT_IMPORTS);
    middleBottomPanel = new JPanel();
    minifyCheckbox = new JCheckBox();
    packageCheckbox = new JCheckBox();
    bottomComponent = new JButton("Merge & Copy");
  }

  private void addComponents() {
    middleBottomPanel.add(new JLabel("Minify"));
    middleBottomPanel.add(minifyCheckbox);
    middleBottomPanel.add(new JLabel("w.o. Package"));
    middleBottomPanel.add(packageCheckbox);

    mainFrame.getContentPane().add(topComponent);
    mainFrame.getContentPane().add(middleComponent);
    mainFrame.getContentPane().add(middleBottomPanel);
    mainFrame.getContentPane().add(bottomComponent);
  }
}
