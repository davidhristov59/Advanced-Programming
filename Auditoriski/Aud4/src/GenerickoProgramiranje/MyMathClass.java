package GenerickoProgramiranje;

import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class MyMathClass {


    public void printStatistics(List<? extends Number> numbers){ //wildcard

        DoubleSummaryStatistics doubleSummaryStatistics = numbers.stream().mapToDouble(i -> i.doubleValue()).summaryStatistics(); //za pecatenje na statistiki

        System.out.println(doubleSummaryStatistics.getMin());
        System.out.println(doubleSummaryStatistics.getMax());
        System.out.println(doubleSummaryStatistics.getAverage());
        System.out.println(doubleSummaryStatistics.getSum());

        double sum = 0;
        for(Number n:numbers){
            sum += (n.doubleValue() - doubleSummaryStatistics.getAverage()) * (n.doubleValue() - doubleSummaryStatistics.getAverage()); //mora vrednost da im dodelime DoubleValue
        }
        System.out.println(Math.sqrt(sum / numbers.size()));
    }

    public static void main(String[] args) {

        MyMathClass myMathClass = new MyMathClass();
        myMathClass.printStatistics(Collections.singletonList(41));
        myMathClass.printStatistics(Collections.singletonList(52));
        myMathClass.printStatistics(Collections.singletonList(72));
        myMathClass.printStatistics(Collections.singletonList(95));

    }

}
