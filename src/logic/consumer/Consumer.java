/*
 * Projekt: RestAdapter
 * Firma:   ABB TS
 * Autor:   M. Bontognali
 *
 * Beschreibung:
 * Dieser Prototyp zeigt, wie in Java ein REST-Client implementiert wird.
 */

import java.util.*;
import logic.consumer.*;

class Consumer {
  public static void main(String[] args) {
    DataObject dataObject = LogicAdapter.getData();
    System.out.println();

//    dataObject = LogicAdapter.getData("x");
//    System.out.println(dataObject);
//
//    dataObject = LogicAdapter.getData("unknown");
//    System.out.println(dataObject);
//
//    dataObject = LogicAdapter.getData("y");
//    System.out.println(dataObject);
//
//    List<Person> list = new ArrayList<>();
//    list.add(new Person("Mike", 25));
//    list.add(new Person("Joe", 30));
//    list.add(new Person("Pet", 35));
//
//    dataObject = new DataObject(false, 11, 0.5, "Hello Producer", list);
//    LogicAdapter.setData(dataObject);
//
//    dataObject = new DataObject(false, 17, 0.7, "Hello Producer 2", list);
//    DataObject do2 = LogicAdapter.calculate(dataObject, 7, "test");
//    System.out.println(do2);
  }
}
