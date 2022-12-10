package com.github.kboeckler.codingameclipboard;

public class TextMerger {

  private String introText;

  private String importText;

  private final StringBuilder fileTextBuilder = new StringBuilder();

  public TextMerger() {
    introText = "// ######################## Merged Source Files together ########################";
  }

  void addFileText(String text, String source) {
    fileTextBuilder
        .append("// ######################## Source File: ")
        .append(source)
        .append(" ########################\n\n");
    fileTextBuilder.append(text);
    fileTextBuilder.append("\n\n");
  }

  void setImportText(String text) {
    importText = "// ######################## Imports: " + " ########################\n\n" + text;
  }

  String getMergedText() {
    return introText + "\n\n" + importText + "\n\n" + fileTextBuilder.toString();
  }
}
