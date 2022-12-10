package com.github.kboeckler.codingameclipboard;

public class ConsoleWriter implements OutputWriter {
  @Override
  public void printOutput(String output) {
    System.out.println(output);
  }
}
