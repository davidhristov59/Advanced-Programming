package WordCount;

import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class WordCount {

    public static void count(InputStream inputStream){

        Scanner scanner = new Scanner(inputStream);
        int l = 0, w = 0, c = 0;

        while(scanner.hasNextLine()){

            String line = scanner.nextLine();
            ++l;//odma cim citame go zgolemuva brojot na linii

            String[] words  = line.split("\\s+");
            w+=words.length;

            c+=line.length();

        }

        System.out.println(String.format("Lines: %d, Words: %d, Characters: %d", l,w,c));

    }

    public static void countWithStreams(InputStream inputStream){

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        LineConsumer lineConsumer = new LineConsumer();

        bufferedReader.lines().forEach(lineConsumer);
        System.out.println(lineConsumer);
    }

}