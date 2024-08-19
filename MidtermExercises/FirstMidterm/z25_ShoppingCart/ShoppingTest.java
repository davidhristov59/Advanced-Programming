package z25_ShoppingCart;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class InvalidOperationException extends Exception{
    public InvalidOperationException(String message) {
        super(message);
    }
}

abstract class Product implements Comparable<Product>{

    public String productID;
    public String productName;
    public Integer productPrice;

    public Product(String productID, String productName, Integer productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public abstract double total_price();

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f", productID, total_price());
    }

    @Override
    public int compareTo(Product o) {
        return Double.compare(this.total_price(), o.total_price());
    }
}

class PS_Product extends Product {

    public double quantity;

    public PS_Product(String productID, String productName, Integer productPrice, double quantity) throws InvalidOperationException{
        super(productID, productName, productPrice);
        if(quantity == 0){
            throw new InvalidOperationException("The quantity of the product with id %s can not be 0.\n" + productID);
        }

        this.quantity = quantity;
    }

    @Override
    public double total_price() {
        return quantity * (double) getProductPrice() / 1000;
    }
}

class WS_Product extends Product{

    public int quantity;

    public WS_Product(String productID, String productName, Integer productPrice, int quantity) throws InvalidOperationException {
        super(productID, productName, productPrice);

        if(quantity == 0){
            throw new InvalidOperationException("The quantity of the product with id %s can not be 0.\n" + productID);
        }

        this.quantity = quantity;
    }

    @Override
    public double total_price() {
        return quantity * getProductPrice();
    }
}

class ShoppingCart{

    List<Product> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(String itemData) throws InvalidOperationException{

        //PS;107965;Flour;409;800.78

        String[] parts = itemData.split(";");

        String type_of_product = parts[0];
        String productID = parts[1];
        String productName = parts[2];
        Integer productPrice = Integer.parseInt(parts[3]);

        if(type_of_product.equals("WS")){
            WS_Product wsProduct = new WS_Product(productID,productName,productPrice,Integer.parseInt(parts[4]));
            items.add(wsProduct);
        }
        else { //PS
            PS_Product psProduct = new PS_Product(productID, productName, productPrice, Double.parseDouble(parts[4]));
            items.add(psProduct);
        }
    }

    public void printShoppingCart(OutputStream os){
        PrintWriter printWriter = new PrintWriter(os);

        items.stream()
                .sorted(Comparator.comparing(Product::total_price).reversed())
                .forEach(product -> {
                   printWriter.println(product);
                });

        printWriter.flush();
    }

    void blackFridayOffer(List<Integer> discountItems, OutputStream os) throws InvalidOperationException{

        if(discountItems.isEmpty()) {
            throw new InvalidOperationException("There are no products with discount.");
        }

        PrintWriter printWriter = new PrintWriter(os);

        List<Product> saleItems = items.stream()
                .filter(i -> discountItems.contains(i.getProductID()))
                .collect(Collectors.toList());

        for(Product product : saleItems){
            printWriter.println(product.toString());
        }

        printWriter.flush();
    }

}

public class ShoppingTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        int items = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < items; i++) {
            try {
                cart.addItem(sc.nextLine());
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }
        }

        List<Integer> discountItems = new ArrayList<>();
        int discountItemsCount = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < discountItemsCount; i++) {
            discountItems.add(Integer.parseInt(sc.nextLine()));
        }

        int testCase = Integer.parseInt(sc.nextLine());
        if (testCase == 1) {
            cart.printShoppingCart(System.out);
        } else if (testCase == 2) {
            try {
                cart.blackFridayOffer(discountItems, System.out);
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Invalid test case");
        }
    }
}