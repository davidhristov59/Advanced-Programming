import java.util.Arrays;
import java.util.stream.DoubleStream;

public class Zad2Matrix {

    public static double suma(double[][] a){
        double zbir = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                zbir += a[i][j];
            }
        }
        return zbir;
    }

    public static double sumStreams(double[][] a){
        //koristime arrays bidejki se matrici

//        return Arrays.stream(a).mapToDouble(row -> Arrays.stream(row).sum()).sum();

        //                                                      dadeno e sum na 2te mesta
        return Arrays.stream(a).mapToDouble(row -> Arrays.stream(row).sum()).sum();

        /*
        Arrays.stream(a) is used to convert the 2D array into a stream of double arrays. This allows us to process each row of the matrix.
        .mapToDouble(row -> Arrays.stream(row).sum()) applies the mapToDouble operation to each row in the stream. For each row, it transforms it into the sum of its elements.
        This is achieved by calling Arrays.stream(row) on each row to convert it into a stream of double values and then applying .sum() to calculate the sum of that row.
        Finally, .sum() is called on the resulting stream of row sums to calculate the sum of all the rows, which is the total sum of all elements in the 2D array

         */

    }

    public static double average(double[][] a) {

        return suma(a)/(a.length * a[0].length); //isto kako ciklusot

    }


    public static void main(String[] args) {

        double[][] matrix = {{1,2,3,4},{5,6,7,8}};

        System.out.println(suma(matrix));
        System.out.println(sumStreams(matrix));
        System.out.println(average(matrix));

    }
}
