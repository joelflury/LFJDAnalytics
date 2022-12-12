/*
 * Projekt: RestAdapter
 * Firma:   ABB TS
 * Autor:   M. Bontognali
 *
 * Beschreibung:
 * Beispieldaten die via Rest übertragen werden.
 */

class Person {
  String name;
  int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        ", age=" + age +
        '}';
  }
}
