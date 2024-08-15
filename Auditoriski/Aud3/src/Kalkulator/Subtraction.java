package Kalkulator;

public class Subtraction implements Strategy{

    @Override
    public double execute(double num1, double num2) {
        return num1 - num2;
    }
}
