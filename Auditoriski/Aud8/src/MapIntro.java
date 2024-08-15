import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapIntro {
    public static void main(String[] args) {

        //mora klucot da bide comparable
        //nema duplikat klucevi
        //O(log n) za dodavanje...
        Map<String, String> treemap = new TreeMap<>();

        treemap.put("FINKI", "FINKI"); //key 1 , vrednost = "FINKI"
        treemap.put("FinKi", "Finki"); //key 1 , vrednost = "FINKI"
        treemap.put("F", "Fakultet"); //key 1 , vrednost = "FINKI"
        treemap.put("I", "Informaticki");

        System.out.println(treemap);
        //gi sortira vo rastecki redosled sto znaci
        //se sortira spored klucot  - F, FI, Fi, I
        //gi gleda po ASCII tabelata

    //-------------------------------------------------------

        //O(1) dodavanje..
        //nema duplikat kluc
        //hash mapata go izmestuva redosledot
        Map<String, String> hashmap = new HashMap<>();

        hashmap.put("FINKI", "FINKI"); //key 1 , vrednost = "FINKI"
        hashmap.put("FinKi", "Finki"); //key 1 , vrednost = "FINKI"
        hashmap.put("F", "Fakultet"); //key 1 , vrednost = "FINKI"
        hashmap.put("I", "Informaticki");

        System.out.println(hashmap);

    //-------------------------------------------------------

        //da ne go izgubime redosledot, ke si ostane ist
        Map<String, String> linkedhashmap = new LinkedHashMap<>();

        linkedhashmap.put("FINKI", "FINKI"); //key 1 , vrednost = "FINKI"
        linkedhashmap.put("FinKi", "Finki"); //key 1 , vrednost = "FINKI"
        linkedhashmap.put("F", "Fakultet"); //key 1 , vrednost = "FINKI"
        linkedhashmap.put("I", "Informaticki");

        System.out.println(linkedhashmap);

    }
}
