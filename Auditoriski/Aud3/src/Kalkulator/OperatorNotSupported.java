package Kalkulator;

public class OperatorNotSupported extends Exception{ //site isklucoci nasleduvaat od Exception

    public OperatorNotSupported(char operator) {
        super(String.format("The operator %c is not supported", operator));
    }
}
