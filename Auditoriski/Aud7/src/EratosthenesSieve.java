import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EratosthenesSieve{

    public static boolean isPrime(int number){
        for (int i = 2; i <= number/2; i++) {
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> numbers = IntStream.range(2,101)
                .boxed() //boxed pravam za streamot da stane stream od integeri, a ne IntStream
                .collect(Collectors.toCollection(ArrayList::new)); //GO SOBIRAME VO ARRAYLIST

        for (int i = 0; i < numbers.size(); i++) {
            if(isPrime(numbers.get(i))){ //i-tiot broj
                for (int j=i+1;j<numbers.size();j++){
                    if(numbers.get(j) % numbers.get(i) == 0){
                        numbers.remove(j);
                        j--;
                    }
                }
            }
        }
        System.out.println(numbers);
    }
}
