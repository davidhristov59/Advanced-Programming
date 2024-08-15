import java.util.stream.IntStream;

public class Zad1StringPrefix {

    public static boolean isPrefix(String str1, String str2) {

        if (str1.length() > str2.length()) {
            return false;
        }

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    //resena so Streams
    public static boolean isPrefixStreams(String str1, String str2) {
        if (str1.length() > str2.length()) {
            return false;
        }

        return IntStream.range(0, str1.length()).allMatch(i -> str1.charAt(i) == str2.charAt(i));
        //forEach vraka void i ne moze da se iskoristi, zatoa se koristi allMatch bidejki vraka boolean i nas toa ni treba


        /*
        kreiraj stream of integers so range od 0 do dolzinata na prviot string. Ovie integers ke se koristat kako indeksi za da imame pristap do karakterite na 2ta stringa
        Ovoj stream ke ni koristi za iteracija na karakterite na 2ta stringa
        allMatch(vraka boolean) terminalnata operacija ke ja koristime ZA DA PROVERIME dali karakterite od s1 se isti so karakterite od s2. Ako se istite karatkerite vrati true, ako ne false

        The allMatch terminal operation in Java streams is used to check if all elements in a stream satisfy a given
        condition or predicate. It returns a boolean value, true if all elements meet the condition, and false if at least one
        element does not meet the condition.
         */

}

    public static void main(String[] args) {

        System.out.println(isPrefix("test", "testing"));
        System.out.println(isPrefix("test", "apple"));
        System.out.println(isPrefix("jad", "jaden"));

        System.out.println(isPrefixStreams("test", "testing"));
        System.out.println(isPrefixStreams("test", "jabolko"));
    }

}
