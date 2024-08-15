import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ReverseListCollection {

    public static<T> void printReverse(Collection<T> collection) {

        //MOZEME I VAKA PREKU KONSTRUKTOR DA GI DODADEME ELEMENTITE VO LISTATA
        List<T> list = new ArrayList<>(collection); //cela elementi od kolekcijata ke ja stavime vo edna lista

//        list.addAll(collection);//ke gi dodademe site elementi od kolekcijata
        //addAll e metod na Collection

        for (int i = list.size()-1; i >= 0 ; i--) { //bidejki treba da pecatime vo obraten redosled
            System.out.print(list.get(i) + " ");
        }

        //2 NACIN
//        Collections.reverse(list);
//        for (T element : list){
//            System.out.print(element + " ");
//        }

    }

    public static void main(String[] args) {

        List<Integer> list = List.of(1,2,3,4,5,6,7,8,9);

        printReverse(list);

    }
}
