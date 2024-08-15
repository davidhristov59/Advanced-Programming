import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//
public class CitanjeOdInputStream {

    public static void main(String[] args) {


//        Scanner scanner = new Scanner(System.in);
//
//        List<String> lines = new ArrayList<>(); //ke gi smestam liniite vo lista
//        //site redovi od ekran
//
//        List<Integer> numbers = new ArrayList<>();
//
//        int n = scanner.nextInt();
//        for (int i = 0; i < n; i++) {
//            numbers.add(scanner.nextInt());
//        }
//
//        while(scanner.hasNextLine()){
//            String line = scanner.nextLine();
//            lines.add(line);
//        }
//
//        System.out.println(lines);

        List<String> inputs = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        inputs = bufferedReader.lines().collect(Collectors.toList());  //site linii da gi procitame so 1 linija kod
        //vraka lines

        System.out.println(inputs);

    }
}

//COMMAND D ZA DA ZASTANEME SO CITANJE - EOF - NE VNESUVAME NISTO




