package com.github.kboeckler.codingameclipboard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FullFileReader {

  private final File file;
  private final String[] ignores;
  private String fullText;
  private final List<String> ignoredRows = new ArrayList<>();

  public FullFileReader(File file, String... ignores) {
    this.file = file;
    this.ignores = ignores;
  }

  void read() {
    StringBuilder textBuilder = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String row;
      while ((row = br.readLine()) != null) {
        boolean ignoreRow = false;
        for (String ignore : ignores) {
          if (row.startsWith(ignore)) {
            ignoreRow = true;
            ignoredRows.add(row);
            break;
          }
        }
        if (!ignoreRow) {
          textBuilder.append(row).append("\n");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
    fullText = textBuilder.toString();
  }

  public String getFullText() {
    return fullText;
  }

  public List<String> getIgnoredRows() {
    return ignoredRows;
  }
}
