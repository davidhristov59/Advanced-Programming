package Kalkulator;

public class Division implements Strategy{

    @Override
    public double execute(double num1, double num2) {
        if(num2 == 0){
            throw new ArithmeticException(); //prvo so isklucoci odime i posle odime so kodot na kraj
        }
        return num1/num2;
    }
}
