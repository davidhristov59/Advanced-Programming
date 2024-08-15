import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class Counter{
    int[] countingArray;

    public Counter(){
        countingArray = new int[10];
    }

    public void addToCounter(int digit){
        countingArray[digit]++;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < countingArray.length; i++) {
            stringBuilder.append(String.format("%d: %d",i,countingArray[i])).append("\n");
        }
        return stringBuilder.toString();
    }
}

class BenfordLawExperiment{

    List<Integer> numbers;
    Counter counter;

    public BenfordLawExperiment(){
        numbers = new ArrayList<>();
        counter = new Counter();
    }

    public void readData(InputStream inputStream){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        //cela lista ke ja zacuvame vo numbers
          numbers = bufferedReader.lines()
                      .map(line -> Integer.parseInt(line))
                      .collect(Collectors.toList());
    }

    public void conductExperiment() {
        numbers.stream()
                .map(number -> getFirstDigit(number))
                .forEach(firstDigit -> counter.addToCounter(firstDigit));
    }

    public int getFirstDigit(int number) {
        while(number >= 10){
            number/=10;
        }
        return number;
    }
}

public class BenfordLawTest {
    public static void main(String[] args) {

        BenfordLawExperiment experiment = new BenfordLawExperiment();
        try {
            experiment.readData(new FileInputStream(""));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        experiment.conductExperiment();
    }
}
