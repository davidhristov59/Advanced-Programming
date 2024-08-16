package z16_MojDDV;

import java.io.*;
import java.security.spec.ECFieldF2m;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

class AmountNotAllowedException extends Exception{
    public AmountNotAllowedException(String message) {
        super(message);
    }
}
class Item{

    private int price;
    private String tax_type;

    public Item(int price, String tax_type) {
        this.price = price;
        this.tax_type = tax_type;
    }

    public double calculate_tax(){
        if(tax_type.equals("A")){
            return price * 0.18;
        }
        else if(tax_type.equals("B")){
            return price * 0.05;
        }
        return 0;
    }

    public double tax_return(){
        return calculate_tax() * 0.15;
    }

    public int getPrice() {
        return price;
    }

    public String getTax_type() {
        return tax_type;
    }
}

class Receipt{

    private String ID;
    List<Item> items;

    public Receipt(String ID, List<Item> items) {
        this.ID = ID;
        this.items = items;
    }

    //107228 1228 V 1116 B 1116 V 1777 A 1777 B 1777 V 1203 A 1203 B 1203 V 1429 V 845 A 845 B 845 V 1070 V 1452 B 1452 V 955 A 955 B 955 V 841 V 789 B 789 V 1531 V
    public Receipt (String line) throws AmountNotAllowedException {

        String[] parts = line.split("\\s+");

        String ID = parts[0];

        int sum = 0;
        for (int i = 1; i < parts.length; i+=2) {
            sum += Integer.parseInt(parts[i]);
        }

        if(sum > 30000){
            throw new AmountNotAllowedException(String.format("Receipt with amount %d is not allowed to be scanned", sum));
        }

        for (int i = 1; i < parts.length - 1; i+=2) {
            this.items.add(new Item(Integer.parseInt(parts[i]),parts[i+1]));
        }
    }

    public int sumOfAmounts(){
        return items.stream().mapToInt(i -> i.getPrice()).sum();
    }

    public double tax_return(){
        return items.stream().mapToDouble(i -> i.tax_return()).sum();
    }

    @Override
    public String toString() {
        return String.format("%10s\t %9d\t %9.5f", ID, sumOfAmounts(), tax_return());
    }
}

class MojDDV{

    List<Receipt> receipts;

    public MojDDV() {
        receipts = new ArrayList<>();
    }

    public void readRecords (InputStream inputStream){

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        bufferedReader.lines().forEach(i -> {
            try {
                receipts.add(new Receipt(i));
            } catch (AmountNotAllowedException e) {
                System.out.println(e.getMessage());
                return;
            }
        });
    }

    public void printTaxReturns (OutputStream outputStream){

        PrintWriter printWriter = new PrintWriter(outputStream);

        for(Receipt receipt : receipts){
            printWriter.println(receipt.toString());
        }

        printWriter.flush();
    }

    public void printStatistics(OutputStream outputStream){

        PrintWriter printWriter = new PrintWriter(outputStream);
        DoubleSummaryStatistics dss = receipts.stream().mapToDouble(i -> i.tax_return()).summaryStatistics();

        printWriter.println(String.format("min:\t%.3f", dss.getMin()));
        printWriter.println(String.format("max:\t%.3f", dss.getMax()));
        printWriter.println(String.format("sum:\t%.3f", dss.getSum()));
        printWriter.println(String.format("count:\t%d", dss.getCount()));
        printWriter.println(String.format("avg:\t%.3f", dss.getAverage()));
    }

}

public class MojDDVTest {

    public static void main(String[] args) {

        MojDDV mojDDV = new MojDDV();

        System.out.println("===READING RECORDS FROM INPUT STREAM===");
        mojDDV.readRecords(System.in);

        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
        mojDDV.printTaxReturns(System.out);

        System.out.println("===PRINTING SUMMARY STATISTICS FOR TAX RETURNS TO OUTPUT STREAM===");
        mojDDV.printStatistics(System.out);

    }
}
