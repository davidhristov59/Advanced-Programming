import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CountCollection {

    public static int count(Collection<Collection<String>> c, String str){

        int counter = 0;

        //mora da se izmine sekoja kolekcija - sto znaci 2 kolekcii ke se izminat
        //kompleknost 0(n^2)

        for(Collection<String> collection:c){ //redicata . Sekoja kolekcija od kolekciite
            for(String element : collection){ //sekoj string vo TAA KOLEKCIJA (prethodnata) (vnatresnata)
                if(element.equals(str)){
                    counter++;
                }
            }
        }

        return counter;
    }

    public static int count2Streams(Collection<Collection<String>> collection, String str) {
        //koga imame kolekcija od kolekcii ako treba nesto da proverime e FLATMAP

        return (int) collection.stream()
                .flatMap(col -> col.stream()) //vo sekoja kolekcija vnatre pravam stream
                //sega ke bide stream od stringovi, ke gi dobieme site stringovi koi sto se sodrzat
                .filter(string -> string.equals(str))
                .count();
    }


        public static void main(String[] args) {


        }
}
