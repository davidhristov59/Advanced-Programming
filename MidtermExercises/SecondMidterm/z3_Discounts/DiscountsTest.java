package z3_Discounts;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Product{
    public int discounted_price;
    public int price;

    public static Comparator<Product> PRODUCT_COMPARATOR =
            Comparator.comparing(Product::discountedPercentage)
                    .thenComparing(Product::absolute_discount)
                    .reversed();

    public Product(int discounted_price, int price) {
        this.discounted_price = discounted_price;
        this.price = price;
    }

    public static Product createProduct(String line){
        String[] parts = line.split(":");

        int discounted_price = Integer.parseInt(parts[0]);
        int price = Integer.parseInt(parts[1]);

        return new Product(discounted_price, price);
    }

    public int discountedPercentage(){
        return (int)(100-((float) discounted_price/price*100));
    }

    public int absolute_discount(){
        return price - discounted_price;
    }

    public int getDiscounted_price() {
        return discounted_price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%2d%% %d/%d", discountedPercentage(), discounted_price, price);
    }
}

class Store{

    List<Product> products = new ArrayList<>();
    String name;

    public static Comparator<Store> STORE_COMPARATOR_AVERAGE =
            Comparator.comparing(Store::averageDiscountPercentage)
                    .reversed()
                    .thenComparing(Store::getName);

    public static Comparator<Store> STORE_COMPARATOR_SUM =
            Comparator.comparing(Store::totalDiscount)
                    .reversed()
                    .thenComparing(Store::getName);

    public Store(List<Product> products, String name) {
        this.products = products;
        this.name = name;
    }

    public static Store generateStore(String line){

        //GAP 501:593  6135:7868  1668:2582  3369:4330  9702:15999  8252:13674  3944:5707  2896:4392  9169:17391

        String[] parts = line.split("\\s+");

        String name = parts[0];

        List<Product> inputs = Arrays.stream(parts)
                .skip(1)
                .map(Product::createProduct)
                .sorted(Product.PRODUCT_COMPARATOR)
                .collect(Collectors.toList());

        return new Store(inputs, name);
    }

    public double averageDiscountPercentage(){
        return products.stream()
                .mapToDouble(Product::discountedPercentage)
                .summaryStatistics()
                .getAverage();
    }

    public int totalDiscount(){
        return products.stream()
                .mapToInt(Product::absolute_discount)
                .sum();
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(name).append("\n");
        stringBuilder.append(String.format("Average discount: %.1f%%\n",averageDiscountPercentage()));
        stringBuilder.append(String.format("Total discount: %d\n", totalDiscount()));
        products.forEach(p -> stringBuilder.append(p).append("\n"));

        return stringBuilder.toString();
    }
}

class Discounts{

    List<Store> stores ;

    public Discounts() {
        stores = new ArrayList<>();
    }

    public int readStores(InputStream inputStream){

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        //vo stores(listata) gi cuvam
        stores = bufferedReader.lines()
                .map(Store::generateStore)
                .collect(Collectors.toList());

        return stores.size();
    }

    public List<Store> byAverageDiscount(){
        return stores.stream()
                .sorted(Store.STORE_COMPARATOR_AVERAGE)
                .limit(3)
                .collect(Collectors.toList());

        //ne go povikuvame metodot bidejki go vimame vo comparatorot
    }

    public List<Store> byTotalDiscount(){
        return stores.stream()
                .sorted(Store.STORE_COMPARATOR_SUM)
                .limit(3)
                .collect(Collectors.toList());
    }

}

class DiscountsTest {
    public static void main(String[] args) {
        Discounts discounts = new Discounts();
        int stores = discounts.readStores(System.in);
        System.out.println("Stores read: " + stores);
        System.out.println("=== By average discount ===");
        discounts.byAverageDiscount().forEach(System.out::println);
        System.out.println("=== By total discount ===");
        discounts.byTotalDiscount().forEach(System.out::println);
    }
}