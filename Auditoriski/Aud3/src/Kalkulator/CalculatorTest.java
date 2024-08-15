package Kalkulator;

import java.util.Scanner;

public class CalculatorTest {

    private static char getFirstCharacter(String string){ //od site yes, Y, y, NO, n, N,Result, RESULT, R, r --> KE KONVERTIRA VO PRVA MALA BUKVA ZA DA MOZAM DA PROVERAM STO VNEL KORISNIKOT
        return string.trim().toLowerCase().charAt(0); //go zimame nultiot karakter - PRVATA BUKVA
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(true){
            Calculator calculator = new Calculator();
            calculator.beginningCalculator();
            while(true){
                String line = scanner.nextLine();
                if(getFirstCharacter(line) == 'r')
                    break;

                String[] parts = line.split("\\s+");
                try {
                    /*
                        + 5
                        * 2.2
                        - 5.0
                        parts[0] - operatorot, parts[1] - brojot
                     */
                    calculator.execute(parts[0].charAt(0), Double.parseDouble(parts[1]));
                    //                     parts[0] -> char ni treba potocno go zimam prviot char(prviot indeks), a vtoriot del parts[1] e brojot i go parsirame vo Double
                    System.out.println(calculator);
                } catch (OperatorNotSupported e) {
                    System.out.println(e.getMessage()); //message vo konstruktorot
                }
            }

            System.out.println("Again? (Y/N)");
            String line = scanner.nextLine();
            if(getFirstCharacter(line) == 'n') //ako ne sakal korisnikot da prodolzi - pisal no, NO ..
                break;

        }

    }

}
