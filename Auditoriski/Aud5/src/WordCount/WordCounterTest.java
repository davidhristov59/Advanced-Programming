package WordCount;

import java.io.*;

public class WordCounterTest {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            InputStream inputStreamFile = new FileInputStream(new File("/Users/davidhristov/Desktop/FINKI /Napredno Programiranje/Auditoriski/Aud5/src/WordCount/filee.txt"));
//            WordCount.count(inputStreamFile);
            WordCount.countWithStreams(inputStreamFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}