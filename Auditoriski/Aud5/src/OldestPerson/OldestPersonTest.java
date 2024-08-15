package OldestPerson;

import java.io.*;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class OldestPersonTest {

    public static Person find(InputStream inputStream){ //ke vraka OldestPerson.Person, ja vrakame licnosta
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));


        return bufferedReader.lines().map(line -> new Person(line)).max(Comparator.naturalOrder()).orElse(new Person("Stefan", 31));  //mapirame . Ke go najde max person - potocno najgolema vozrast
//                                                                                                 koristime orElse ako ne najde nisto, ako ima prazna datoteka ke dade sTEFAN...

        //Ja barame licnosta koja e najstara
    }


    public static void main(String[] args) {

        try {
            InputStream inputStream = new FileInputStream(new File("/Users/davidhristov/Desktop/FINKI /Napredno Programiranje/Auditoriski/Aud5/src/OldestPerson/persons.txt"));
            System.out.println(OldestPersonTest.find(inputStream));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
