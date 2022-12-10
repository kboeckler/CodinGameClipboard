package com.github.kboeckler.codingameclipboard;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ClipboardWriter implements OutputWriter {

  @Override
  public void printOutput(String output) {
    StringSelection stringSelection = new StringSelection(output);
    Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
    clpbrd.setContents(stringSelection, null);
  }
}
