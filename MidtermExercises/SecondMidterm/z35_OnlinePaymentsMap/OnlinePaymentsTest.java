package z35_OnlinePaymentsMap;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Product{

    public String name;
    public int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }



    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s %d", name, price);
    }
}

class Student{

    String index;
    List<Product> products;

    public Student(String index){
        this.index = index;
        products = new ArrayList<>();
    }

    public Student(String index, List<Product> products) {
        this.index = index;
        this.products = products;
    }


    public int netoSum(){

        return products.stream()
                .mapToInt(Product::getPrice)
                .sum();
    }

    public int bank_provision(){

        int fee = (int) Math.round(netoSum() * 1.14 / 100.00);

        if(fee < 3){
            fee = 3;
        }

        else if(fee > 300){
            fee=300;
        }

        return fee;
    }

    public int totalSumStudents(){
        return bank_provision() + netoSum();
    }

    public String getIndex() {
        return index;
    }

    @Override
    public String toString() {

        AtomicInteger atomicInteger = new AtomicInteger(1);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("Student: %s Net: %d Fee: %d Total: %d\nItems:\n", index, netoSum(), bank_provision(), totalSumStudents()));
        stringBuilder.append(
                products.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .map(product -> (atomicInteger.getAndIncrement()) + ". " + product.toString())
                .collect(Collectors.toList()));

        return stringBuilder.toString();
    }

}

class OnlinePayments{

    private final Map<String, Student> students;

    public OnlinePayments() {
        students = new HashMap<>();
    }

    public void readItems (InputStream is){

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

        List<String> inputs = bufferedReader.lines().collect(Collectors.toList());

        for(String input : inputs){

            //151020;Административно-материјални трошоци и осигурување;750

            String[] parts = input.split(";");

            String index = parts[0];
            String description = parts[1];
            int price = Integer.parseInt(parts[2]);

            students.putIfAbsent(index, new Student(index));
            students.get(index).products.add(new Product(description, price));
        }

    }

    public void printStudentReport(String index, OutputStream os){

        PrintWriter printWriter = new PrintWriter(os);

        if(students.containsKey(index)){
            printWriter.println(students.get(index));
        }
        else {
            printWriter.println(String.format("Student %s not found", index));
        }

        printWriter.flush();
    }

}

public class OnlinePaymentsTest {
    public static void main(String[] args) {
        OnlinePayments onlinePayments = new OnlinePayments();

        onlinePayments.readItems(System.in);

        IntStream.range(151020, 151025).mapToObj(String::valueOf).forEach(id -> onlinePayments.printStudentReport(id, System.out));
    }
}
