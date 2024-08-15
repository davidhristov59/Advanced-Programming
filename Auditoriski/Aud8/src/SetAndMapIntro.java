
//set e mnozestvo vo koe nema da se sluci da ima  duplikati

import java.util.*;

public class SetAndMapIntro {
    public static void main(String[] args) {

        //TREESET KORISTIME VO ZADACI VO KOI SE TRGAAT DUPLIAKTITE I TREBA NESTO DA SE SORTIRA

        //Od Set(najvisoko na hierarhijata) izveduvame TreeSet, HashSet
        Set<Integer> treeIntSet = new TreeSet<>(); //mora elementite da bidat comparable

        //pristap O(logn), iteriranje 0(nlogn), dodavanje 0(logn), brishenje 0(nlogn)

        Set<Integer> treeIntSet2 = new TreeSet<>(Comparator.reverseOrder());//ako sakame vo Opagacki redosled

        //treeSet gi sortira vo rastecki redosled
        for (int i = 0; i <= 10; i++) {
            treeIntSet2.add(i);
        }

        for (int i = 0; i <= 10; i++) {
            treeIntSet.add(i);
        }

        System.out.println(treeIntSet2);
        System.out.println(treeIntSet);

        //----------------------------------------------------------------------

        //HASHSET GI TRGA DUPLIKATITE
        //vnesuvanje na elementi so O(n)
        Set<String> hashset = new HashSet<>();

        for (int i = 0; i <= 10; i++) {
            hashset.add("FINKI");
            hashset.add("Napredno");
            hashset.add("Programiranje");
        }

        System.out.println(hashset);


        //----------------------------------------------------------------------

        //za da se zacuva redosledot na vnesuvanje
        //NE SORTIRA
        Set<String> linkedHashSet = new LinkedHashSet<>();

        for (int i = 0; i <= 10; i++) {
            linkedHashSet.add("FINKI");
            linkedHashSet.add("finki");
            linkedHashSet.add("napredno");

        }
        System.out.println(linkedHashSet);
    }
}


