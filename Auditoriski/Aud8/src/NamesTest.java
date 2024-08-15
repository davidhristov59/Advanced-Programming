import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Name implements Comparable<Name>{

    String name;
    int frequency;

    public Name(String name, int frequency) {
        this.name = name;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(Name o) {
        return Integer.compare(this.frequency, o.frequency);
    }
}

public class NamesTest {

        private static Map<String, Integer> readNamesWithGroupBy(InputStream inputStream) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            return bufferedReader.lines()
                    .map(line -> {
                       String[] parts = line.split("\\s+");
                        String name = parts[0];
                        int frequency = Integer.parseInt(parts[1]);

                        return new Name(name, frequency);
                    }).collect(Collectors.groupingBy(nameObj -> nameObj.name,
                            Collectors.summingInt(nameObj -> nameObj.frequency)));
            }


        private static Map<String, Integer> readNamesWithCollectorToMap(InputStream inputStream){

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        //VAKA DIREKTNO MOZE DA NAPRAVIME RETURN NA CELIOT STATEMENT
         return bufferedReader.lines().map(line -> {
            String[] parts = line.split("\\s+");
            String name = parts[0];
            int frequency = Integer.parseInt(parts[1]);
            return new Name(name, frequency);
        }).collect(Collectors.toMap( //DA SE SOBERAT SITE VO EDNA MAPA
                nameObject -> nameObject.name, //KEY
                nameObj2 -> nameObj2.frequency //VALUE
        ));

    }

    //klucot ke bide imeto, a value ke bide frekvencijata na toa ime

    //ke vrati Mapa kade funkcijata e klucot, a frekvencijata e
    private static Map<String, Integer> readNames(InputStream inputStream){

        //ke cita od inputStream kade sto klucot e imeto, a vrednosta e cestotata na pojavuvanje
        Map<String, Integer> frequencyName = new HashMap<>(); //koga ne eni e bitno elementite da se sortirani

        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");

            String name = parts[0];
            int frequency = Integer.parseInt(parts[1]);

            //ke gi stavime vo mapa
            frequencyName.put(name,frequency); //key i value
        }
        return frequencyName; //ke ja vratime kreiranata mapa
    }

    public static void main(String[] args) {

        try {
            Map<String, Integer> boyNames = readNames(new FileInputStream(""));
            Map<String, Integer> girlNames = readNames(new FileInputStream(""));

            Set<String> allNames = new HashSet<>(); //da nemame duplikati. pravime Set - MNOZESTVO NA SITE IMINJA

            allNames.addAll(boyNames.keySet()) ; //ke gi stavime site MASKI IMINJA
            allNames.addAll(girlNames.keySet()); //ke gi stavime site ZENSKI IMINJA

            //1 NAACIN
            //UNISEX IMINJA
            allNames.stream()
                    .filter(name -> boyNames.containsKey(name) && girlNames.containsKey(name)) //ke gi filtrirame iminjata koi se naogaat i vo maskata i vo zenskata mapa
                    .map(name -> new Name(name,boyNames.get(name)+girlNames.get(name)))
                    .sorted(Comparator.reverseOrder())
                    .forEach(name -> System.out.println(String.format("%s: %d", name, boyNames.get(name)+girlNames.get(name)))); //kolku pati imeto se naoga i kako masko i kako zensko ime
                                                                                        //frekvencija za maskite iminja i frekvencija za zenskite iminja

            //2 NACIN - bez pravenje klasa i dr raboti
            Map<String, Integer> unisexNames = new HashMap<>();

            //sort the map by value in descending order
            unisexNames.entrySet() //entrySet vraka mnozestvo od site parovi na kluc i vrednost
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) //sakame da sporeduvame spored VREDNOSTA I TOA SPOREDUVANJE DA BIDE VO OPAGACKI REDOSLED
                    .forEach(entry -> System.out.println(String.format("%s -> %d",entry.getKey(), entry.getValue()))); //imeto ke go dobieme kako entry.getKey(), a vrednosta kako getValue()

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
