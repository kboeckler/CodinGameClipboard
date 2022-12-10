package com.github.kboeckler.codingameclipboard;

public class Starter {

  private static final ClipboardManagerFactory factory = new ClipboardManagerFactory();

  public static void main(String[] args) {
    if (args.length > 0) {
      runInConsole(new Args(args));
    } else {
      runInGui();
    }
  }

  private static void runInGui() {
    new SimpleCopyToClipboardGui(new ClipboardManager(new ClipboardWriter(), new MinifyService()));
  }

  private static void runInConsole(Args args) {
    ClipboardManager manager = factory.createManager(args);
    manager.executeMergeToClipboard();
  }
}
