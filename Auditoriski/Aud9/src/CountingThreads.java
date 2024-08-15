import java.util.ArrayList;
import java.util.List;

class Printer extends Thread{

    int number;

    public Printer(int number) {
        this.number = number;
    }

    @Override
    public void run() {     //ke treba da pecati odreden broj
        System.out.println(number);
    }
}

public class CountingThreads {

    public static void main(String[] args) {

        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= 1000; i++) {

            int number = i;
            threads.add(new Thread(new Runnable() {

                @Override
                public void run() {
                    System.out.println(number);
                }
            }));
        }

        threads.stream().forEach(thread -> thread.start()); //morame start() prvo da povikame
        //niskata da startne

        //da pocekame site niski da zavrsat
        threads.stream().forEach(printer -> {
            try {
                printer.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        //ne moze da se garantira koj thread e prv , gi pecati random threadovi, ne po red
        //ne MOZEME DA OSIGURAME SEKVENCIJALEN REDOSLED, NO MOZEME DA OSIGURAME PARALELEN REDOSLED

    }
}
