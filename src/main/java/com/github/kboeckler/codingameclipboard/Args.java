package com.github.kboeckler.codingameclipboard;

public class Args {

  private final String sourceFolder;
  private final String imports;
  private final boolean shallMinify;
  private final boolean shallRemovePackageDeclarations;
  private final boolean shallCopyToClipboard;

  public Args(String[] input) {
    String src = null;
    if (input.length == 0) {
      throw new IllegalArgumentException("Not enough arguments");
    }
    boolean minify = false;
    boolean removePackage = true;
    boolean copyToClipboard = false;
    for (String part : input) {
      if (part.startsWith("-")) {
        if (part.equals("-m")) {
          minify = true;
        } else if (part.equals("-p")) {
          removePackage = false;
        } else if (part.equals("-c")) {
          copyToClipboard = true;
        }
      } else {
        if (src != null) {
          throw new IllegalArgumentException("Multiple source folders given");
        }
        src = part;
      }
    }
    sourceFolder = src;
    imports = SimpleCopyToClipboardGui.DEFAULT_IMPORTS;
    shallMinify = minify;
    shallRemovePackageDeclarations = removePackage;
    shallCopyToClipboard = copyToClipboard;
  }

  public String getSourceFolder() {
    return sourceFolder;
  }

  public String getImports() {
    return imports;
  }

  public boolean isShallMinify() {
    return shallMinify;
  }

  public boolean isShallRemovePackageDeclarations() {
    return shallRemovePackageDeclarations;
  }

  public boolean isShallCopyToClipboard() {
    return shallCopyToClipboard;
  }
}
