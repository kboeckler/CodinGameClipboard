package com.github.kboeckler.codingameclipboard;

public class ClipboardManagerFactory {

  ClipboardManager createManager(Args args) {
    OutputWriter outputWriter;
    if (args.isShallCopyToClipboard()) {
      outputWriter = new ClipboardWriter();
    } else {
      outputWriter = new ConsoleWriter();
    }
    ClipboardManager manager = new ClipboardManager(outputWriter, new MinifyService());
    if (manager.setSourceFolder(args.getSourceFolder())) {
      manager.setImports(args.getImports());
      manager.setShallMinify(args.isShallMinify());
      manager.setShallRemovePackageDeclaration(args.isShallRemovePackageDeclarations());
    } else {
      System.err.println("Source Folder could not be found");
      System.exit(-1);
    }
    return manager;
  }
}
