package seedu.addressbook.ui;

import java.io.*;

public class TextFormatter {

  /** A decorative prefix added to the beginning of lines printed by AddressBook */
  private static final String LINE_PREFIX = "|| ";

  /** A platform independent line separator. */
  private static final String LS = System.lineSeparator();

  private final PrintStream out;

  public TextFormatter(PrintStream out) {
    this.out = out;
  }

  public void print(String s) {
    System.out.print(LINE_PREFIX + s);
  }

  public void println(String s) {
    System.out.println(LINE_PREFIX + s.replace("\n", LS + LINE_PREFIX));
  }
}
