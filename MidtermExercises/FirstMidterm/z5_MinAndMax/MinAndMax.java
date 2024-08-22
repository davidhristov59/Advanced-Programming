package z5_MinAndMax;

import java.util.Scanner;

class MinMax<T extends Comparable<T>>  {

    T minElement;
    T maxElement;
    int total;
    int minCount;
    int maxCount;

    public MinMax() {
        total = 0;
        minCount = 0;
        maxCount = 0;
    }

    public T max(){
        return maxElement;
    }

    public T min(){
        return minElement;

    }

    public void update(T element){

        if(element == null){
            return;
        }

        if(minElement == null || minElement.compareTo(element) > 0){
            minElement = element;
            minCount=1;
        }
        else if(minElement.compareTo(element) == 0){
            minCount++;
        }

        if(maxElement == null || maxElement.compareTo(element) < 0){
            maxElement = element;
            maxCount=1;
        }
        else if(maxElement.compareTo(element) == 0){
            maxCount++;
        }

        total++;
    }


    @Override
    public String toString() {

        return String.format("%s %s %d\n",minElement,maxElement ,total-(minCount+maxCount));
    }
}

public class MinAndMax {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        MinMax<String> strings = new MinMax<String>();
        for(int i = 0; i < n; ++i) {
            String s = scanner.next();
            strings.update(s);
        }
        System.out.println(strings);
        MinMax<Integer> ints = new MinMax<Integer>();
        for(int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            ints.update(x);
        }
        System.out.println(ints);
    }
}