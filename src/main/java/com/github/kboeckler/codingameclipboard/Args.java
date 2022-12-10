package com.github.kboeckler.codingameclipboard;

import java.util.Arrays;
import java.util.stream.Collectors;

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
    String specifiedImports = SimpleCopyToClipboardGui.DEFAULT_IMPORTS;
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
        } else if (part.startsWith("-i=")) {
          specifiedImports = importPackagesToImportStatements(part.replaceFirst("-i=", ""));
        }
      } else {
        if (src != null) {
          throw new IllegalArgumentException("Multiple source folders given");
        }
        src = part;
      }
    }
    sourceFolder = src;
    imports = specifiedImports;
    shallMinify = minify;
    shallRemovePackageDeclarations = removePackage;
    shallCopyToClipboard = copyToClipboard;
  }

  private String importPackagesToImportStatements(String packages) {
    String[] singlePackages = packages.split(";");
    return Arrays.stream(singlePackages)
        .map(p -> "import " + p + ";")
        .collect(Collectors.joining("\n"));
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
