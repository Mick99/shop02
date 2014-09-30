package util;

public class ConsoleUtil {
  public static void printTitle(Object message, char decorationChar, final int LINE_WIDTH) {
    String output = "";
    for (int i = 0; i < LINE_WIDTH; i++) {
      output += decorationChar;
    }
    output += "\n";
    final String firstLine = output;
    System.out.print(firstLine);
    int leftMargin = (LINE_WIDTH - 2 + message.toString().length()) / 2;
    int rightMargin = LINE_WIDTH - 1 - leftMargin;
    String titleFormat = decorationChar + "%" + leftMargin + "s" + "%" + rightMargin + "s%n";
    System.out.print(String.format(titleFormat, message, decorationChar+""));
    System.out.print(firstLine);
  }
  /**
   * noch nicht komplett
   * @param message
   * @param decorationChar
   * @param LINE_WIDTH
   * @param isTitle
   */
  public static void printTitle(Object message) {
    printTitle(message, '#', 80);
  }
  public static void format(String StringFormat, Object... objects) {
    print(String.format(StringFormat, objects));
  }
  public static void print(Object message) {
//    int margin = (80 - message.length() - 2) / 2;
//    String decoration = "";
//    for (int i = 0; i < margin; i++) {
//      decoration += '=';
//    }
    System.out.format("=======> %s%n", message);
  }
}
