import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArrangeLetters {

    public static String arrangeSentence(String sentence){

       return Arrays.stream(sentence.split("\\s+")) //ja delime recenicata na zborovi
                .map(word -> arrangeWord(word)) //sekoj zbor mu pravime arrange
                .sorted()
                .collect(Collectors.joining(" ")); //ke gi razdeli zborovite koa ke printa
    }

    public static String arrangeWord(String word){

        return word.chars() //gi zemavme karakterite
                .sorted() //sortiravme
                .mapToObj(i -> String.valueOf((char) i)) //go mapiravme sekoj karakter vo String, potocno vo char go kastiravme
                .collect(Collectors.joining()); //String vraka joining sto znaci ni vrati stringovi
                                //tuka joining() bez parametar vnatre bidejki treba spoeni da se zborovite

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sentence = scanner.nextLine();

        System.out.println(ArrangeLetters.arrangeSentence(sentence));
    }
}
