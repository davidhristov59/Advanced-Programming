package z2_Book;

import java.util.*;
import java.util.stream.Collectors;


class Book{

    String title;
    String category;
    float price;
    public static final Comparator<Book> BOOK_COMPARATOR = Comparator.comparing(Book::getTitle).thenComparing(Book::getPrice);
    public static final Comparator<Book> BOOK_COMPARATOR_CHEAPEST = Comparator.comparing(Book::getPrice).thenComparing(Book::getTitle);

    public Book(String title, String category, float price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        //Book A (A) 29.41
        return String.format("Book %s (%s) %.2f", title, category, price);
    }
}

class BookCollection{

    //Map<String, Book> books_map;
    List<Book> books_list;

    public BookCollection(){
        //books_map = new HashMap<>();
        books_list = new ArrayList<>();
    }

    public void addBook(Book book){
        books_list.add(new Book(book.title, book.category, book.price));
    }

    public void printByCategory(String category){

        books_list.stream()
                .filter(c -> c.category.equalsIgnoreCase(category))
                .sorted(Book.BOOK_COMPARATOR)
                .forEach(System.out::println);
    }

    public List<Book> getCheapestN(int n){

        return books_list.stream()
                .sorted(Book.BOOK_COMPARATOR_CHEAPEST)
                .limit(n)
                .collect(Collectors.toList());
    }

}

public class BooksTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        BookCollection booksCollection = new BookCollection();
        Set<String> categories = fillCollection(scanner, booksCollection);
        System.out.println("=== PRINT BY CATEGORY ===");
        for (String category : categories) {
            System.out.println("CATEGORY: " + category);
            booksCollection.printByCategory(category);
        }
        System.out.println("=== TOP N BY PRICE ===");
        print(booksCollection.getCheapestN(n));
    }

    static void print(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    static TreeSet<String> fillCollection(Scanner scanner, BookCollection collection) {
        TreeSet<String> categories = new TreeSet<String>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            Book book = new Book(parts[0], parts[1], Float.parseFloat(parts[2]));
            collection.addBook(book);
            categories.add(parts[1]);
        }
        return categories;
    }
}
