import java.util.ArrayList;

class Student{
    String id;

    public Student(String id){
        this.id = id;
    }
}

public class ArrayListIntro {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            strings.add(String.format("NP%d",i));
        }

        System.out.println(strings);

        strings.get(2); //go zimame indexot
//        strings.addAll(); //druga kolekcija da ja dodaeme na krajot na postoeckata lista

        strings.contains("NP"); //dali se sodrzi nekoj string

        strings.stream().forEach(str -> System.out.println(str));

        strings.removeIf(str -> str.startsWith("NP")); //izbrisi ako pocnuvaat so NP . Ke gi izbrise stringovit sto pocnuvaat so NP
    }
}
