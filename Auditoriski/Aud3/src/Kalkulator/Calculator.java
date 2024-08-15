package Kalkulator;

//3 VAZNI RABOTI

//1.kako pravilno da citame i parsirame input
//2.kako pravilno da se spravuvame so isklucoci
//3.upotreba na design pattern - strategy

public class Calculator{

    private double state = 0.0; //vo momentot kolku e vrednosta na kalkulatorot
    private Strategy strategy; //cuvame strategija

    public Calculator() {
        state = 0.0;
        strategy = new Addition();
    }

    public String execute(char operator, double value) throws OperatorNotSupported {
        if(operator == '+'){
            strategy = new Addition();
        }
        else if(operator == '-'){
            strategy = new Subtraction();
        }
        else if(operator == '*'){
            strategy = new Multiplication();
        }
        else if(operator == '/') {
            strategy = new Division();
        }
        else {
            //VAKA NIKAKO SO TRY/CATCH TUKA  - SE KRATAT POENI
//            try {
//                throw new OperatorNotSupported(operator);
//            } catch (OperatorNotSupported e) {
//                throw new RuntimeException(e);
//            }

            throw new OperatorNotSupported(operator); //STAVAME EXCEPTION - METHOD SIGNATURE (throws)

        }

        state = strategy.execute(state,value); //so ovaa linija kod ke izvrsam sve. Koga ke se povika execute, ke se povika implementacijata na Addition
        return String.format("result %c %f = %f", operator,value,state);
    }

    public void beginningCalculator(){ //ke go isprinta kalkulatorot na pocetok
        System.out.println("Calculator is ON.");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("result = %f", state);
    }
}