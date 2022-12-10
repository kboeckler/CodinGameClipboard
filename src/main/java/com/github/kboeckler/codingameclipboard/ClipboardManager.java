package com.github.kboeckler.codingameclipboard;

import java.io.File;
import java.util.List;

public class ClipboardManager {

  private FileFinder finder;
  private String imports;
  private boolean shallMinify;
  private boolean shallRemovePackage;

  private final OutputWriter outputWriter;
  private final MinifyService minifier;

  public ClipboardManager(OutputWriter outputWriter, MinifyService minifier) {
    this.outputWriter = outputWriter;
    this.minifier = minifier;
  }

  boolean setSourceFolder(String sourceFolder) {
    File folder = new File(sourceFolder);
    if (folder.isDirectory()) {
      finder = new FileFinder(folder);
      return true;
    }
    return false;
  }

  void setShallMinify(boolean shallMinify) {
    this.shallMinify = shallMinify;
  }

  public void setShallRemovePackageDeclaration(boolean shallRemovePackage) {
    this.shallRemovePackage = shallRemovePackage;
  }

  void setImports(String imports) {
    this.imports = imports;
  }

  void executeMergeToClipboard() {
    List<File> files = readFiles();
    String mergedText = mergeTextFromFiles(files);
    mergedText = minifyTextIfWished(mergedText);
    copyTextToClipboard(mergedText);
  }

  private List<File> readFiles() {
    return finder.findSourceFiles();
  }

  private String mergeTextFromFiles(List<File> files) {
    TextMerger merger = new TextMerger();
    for (File f : files) {
      String[] ignoreRowsBeginningWith = getIgnoreRows();
      FullFileReader reader = new FullFileReader(f, ignoreRowsBeginningWith);
      reader.read();
      merger.addFileText(reader.getFullText(), f.getName());
    }
    merger.setImportText(imports);
    return merger.getMergedText();
  }

  private String[] getIgnoreRows() {
    if (shallRemovePackage) {
      return new String[] {"package ", "import "};
    }
    return new String[] {"import "};
  }

  private String minifyTextIfWished(String sourceText) {
    if (shallMinify) {
      return minifier.minifyIfPossible(sourceText);
    }
    return sourceText;
  }

  private void copyTextToClipboard(String mergedText) {
    outputWriter.printOutput(mergedText);
  }
}
