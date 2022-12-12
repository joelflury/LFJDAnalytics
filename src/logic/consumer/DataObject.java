/*
 * Projekt: RestAdapter
 * Firma:   ABB TS
 * Autor:   M. Bontognali
 *
 * Beschreibung:
 * Beispieldaten die via Rest übertragen werden.
 */

import java.util.List;

class DataObject {
  private boolean bool;
  private int number;
  private double number2;
  private String text;
  private List<Person> list;

  public DataObject(boolean bool, int number, double number2, String text, List<Person> list) {
    this.bool = bool;
    this.number = number;
    this.number2 = number2;
    this.text = text;
    this.list = list;
  }

  @Override
  public String toString() {
    return "DataObject{" +
        "bool=" + bool +
        ", number=" + number +
        ", number2=" + number2 +
        ", text='" + text + '\'' +
        ", list=" + list +
        '}';
  }
}
