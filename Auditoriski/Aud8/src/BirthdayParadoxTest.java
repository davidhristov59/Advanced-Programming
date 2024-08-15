import java.util.HashSet;
import java.util.Random;

class BirthdayPartyParadox{

    class Trial{

        static Random RANDOM = new Random();

        static double trial(int people){

            HashSet<Integer> existing_birthdays = new HashSet<>();

            int sameBirthdays = 0;

            for (int i = 0; i < people; i++) {
                int randomBirthday = RANDOM.nextInt(1,366); //e exclusive i ne ja vklucuva granicata

                if(existing_birthdays.contains(randomBirthday)){
                    sameBirthdays++;
                }

                else {
                    existing_birthdays.add(randomBirthday); //bez razlika na toa dali postoi ili ne ke dodademe vo setot
                }
            }
            return (float) sameBirthdays / people;
        }

    }

    public void conductExperiment(){

    }


}

public class BirthdayParadoxTest {
    public static void main(String[] args) {

        System.out.println(BirthdayPartyParadox.Trial.trial(50));
    }
}
