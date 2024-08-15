package OldestPerson;

//public class Person implements Comparable<Person>{
//    String name;
//    int age;
//
//    public Person(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    public Person(String line){ //pravime uste eden konstruktor i od ovaa linija ke gi izdvoime site raboti koi ni trebaat
//        String[] parts = line.split("\\s+");
//        this.name = parts[0];
//        this.age = Integer.parseInt(parts[1]);
//    }
//
//    @Override
//    public String toString() {
//        return "OldestPerson.Person{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                '}';
//    }
//
//    @Override
//    public int compareTo(Person o) { //sporedba spored vozrasta
//        return Integer.compare(this.age, o.age);
//    }
//}

import java.util.Comparator;

public class Person implements Comparable<Person> {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String line){ //pravime uste eden konstruktor i od ovaa linija ke gi izdvoime site raboti koi ni trebaat

        String[] parts = line.split("\\s+");
        this.name = parts[0];
        this.age = Integer.parseInt(parts[1]);

    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.age, o.age);
    }
}