import java.util.*;

class StatsThread extends Thread{

    //od kaj do kade vo glavnata niza ke bara koi se max min...
    int start;
    int end;

    IntSummaryStatistics intSummaryStatistics;

    public StatsThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start  ; i < end; i++) {
            intSummaryStatistics.accept(ParallelSearch.ARRAY[i]); //sekoj element ke go dodaeme vo IntSummaryStatistics
        }
    }

    @Override
    public String toString() {
        return "StatsThread{" +
                "start=" + start +
                ", end=" + end +
                ", intSummaryStatistics=" + intSummaryStatistics +
                '}';
    }
}

public class ParallelSearch {

    static int NUMBER_OF_ELEMENTS = 1000000;
    static int NUMBER_OF_THREADS = 100;
    static int[] ARRAY ;
    static Random RANDOM = new Random();

    public static void main(String[] args) {

        ARRAY = RANDOM.ints(NUMBER_OF_ELEMENTS,
                        1,
                        11)
                        .toArray();

//        System.out.println(Arrays.stream(ARRAY).summaryStatistics());

        List<StatsThread> threads = new ArrayList<>();
        int subArrayLength = NUMBER_OF_ELEMENTS / NUMBER_OF_THREADS;
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            int start = i * subArrayLength;
            int end = (i+1) * subArrayLength;
            threads.add(new StatsThread(start, end));
        }

        for (StatsThread thread : threads) {
            thread.start();
        }

        for (StatsThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        threads.forEach(statsThread -> System.out.println(statsThread));

        int min = threads.stream().mapToInt(t -> t.intSummaryStatistics.getMin()).min().getAsInt();
        int max = threads.stream().mapToInt(t -> t.intSummaryStatistics.getMax()).max().getAsInt();
    }
}
