import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FunctionalInterfaceIntro {
    public static void main(String[] args) {

        /*
       Comparator<String> comparator = new Comparator<String>() {
           @Override
           public int compare(String o1, String o2) {
               return 0;
           }
       };

         */

        /*
        Optional<String> optionalS = Optional.of("bam");
        optionalS.get(); //bam
        optionalS.isPresent(); //true
        optionalS.orElse("fallback"); //bam

        optionalS.ifPresent((s) -> System.out.println(s.charAt(0))); //b

         */

        List<String> strings = new ArrayList<>();

        strings.add("test");
        strings.add("balbla");
        strings.add("makedinija");
        strings.add("nApreddno programiranje");

        //function  - functionalInterface - se koristi za MAP function to convert one type into another
        Function<String, Integer> lengthFunction = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) { //ima 1 metod Function - prima od tip String i vraka Integer
                return s.indexOf("a");
            }
        };

        //lambda izraz
//        Function<String, Integer> lengthFunction = s -> s.indexOf("a");

        //eden string da go pretvoram vo Integer
        System.out.println(strings.stream().map(lengthFunction).collect(Collectors.toList())); //ke go kolektiram vo edna lista

        //---------------------------------------------------------------------------

        //predikat - kako if uslov
        Predicate<Integer> positiveNumber  = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) { //ima 1 metod vo nego , vraka boolean, dali nekoj uslov e ispolnet ili ne
                return integer > 0;
            }
        };

        Predicate<Integer> posNumber = num -> num > 0;

        //lambda expression
//        Predicate<Integer> positiveNumberr  = integer -> { //ima 1 metod vo nego , vraka boolean, dali nekoj uslov e ispolnet ili ne
//            return integer > 0;
//        };


        //            Predicates are used with FILTER stream
        System.out.println(strings.stream().map(lengthFunction).filter(positiveNumber).collect(Collectors.toList())); //ke go kolektiram vo edna lista

        //---------------------------------------------------------------------------

        //Supplier              - ne primame vrednosti
        Supplier<Integer> supplier = () -> { //ne prima vrednosti, samo vraka nesto
            return new Random().nextInt();
        };

//        Supplier<Integer> supplier = new Supplier<Integer>() {
//            @Override
//            public Integer get() { //ne prima vrednosti, samo vraka nesto
//                return new Random().nextInt();
//            }
//        };

        for (int i = 0; i < 20; i++) {
            System.out.println(supplier.get()); //moram da go zemam brojot
        }


        //---------------------------------------------------------------------------

        //Consumer - za pecatenje raboti
        Consumer<Integer> consumer = integer -> { //prima argument, ama ne vraka nisto
            System.out.println(integer);
        };

//        Consumer<Integer> consumer = new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) { //prima argument, ama ne vraka nisto
//                System.out.println(integer);
//            }
//        };

        for (int i = 0; i < 50; i++) {
            consumer.accept(i); //prifati go brojot
        }

    }
}

