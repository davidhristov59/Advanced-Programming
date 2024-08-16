package z1_ShapesApplication;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Square{

    public int side;

    public Square(int side) {
        this.side = side;
    }

    public int perimeter(){
        return side * 4;
    }

}

class Canvas{

    private String ID;
    List<Square> squares;

    public Canvas(String ID, List<Square> squares) {
        this.ID = ID;
        this.squares = squares;
    }

    public static Canvas createCanvas(String line){
        String[] parts = line.split("\\s+");

        String ID = parts[0];

        List<Square> squareList = Arrays.stream(parts)
                .skip(1)
                .map(Integer::parseInt)
                .map(Square::new)
                .collect(Collectors.toList());

        return new Canvas(ID, squareList);
    }

    public int squares_count(){
        return squares.size();
    }

    public int total_squares_perimeter(){
        return squares.stream().mapToInt(s -> s.perimeter()).sum();
    }

    public String getID() {
        return ID;
    }

    public List<Square> getSquares() {
        return squares;
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", ID, squares_count(), total_squares_perimeter());
    }
}

class ShapesApplication{

    public List<Canvas> canvases;

    public ShapesApplication() {
        this.canvases = new ArrayList<>();
    }

    public int readCanvases (InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        canvases= bufferedReader
                .lines()
                .map(Canvas::createCanvas)
                .collect(Collectors.toList());

        bufferedReader.close();

        return canvases.stream().mapToInt(s -> s.squares.size()).sum(); //GI SOBIRA SITE KOLKU VKUPNO SE PROCITANI
    }

    //najgolem perimetar se bara
    public void printLargestCanvasTo (OutputStream outputStream){

        PrintWriter printWriter = new PrintWriter(outputStream);

        Canvas max_perimeter = canvases.stream()
                .max(Comparator.comparing(Canvas::total_squares_perimeter))
                .get();

        printWriter.println(max_perimeter);

        printWriter.flush();
    }

}

public class Shapes1Test {
    public static void main(String[] args) {
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        try {
            System.out.println(shapesApplication.readCanvases(System.in));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);

    }
}