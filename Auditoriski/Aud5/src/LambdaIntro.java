
interface Operation{

    //eden i edinstven metod
    //functional interface - IMA SAMO 1 METOD I MOZE OD NIV DA NAPISEME LAMBDA IZRAZ
    //lambdas can be used ONLY IF THERE IS ONE METHOD IN THE INTERFACE
    int execute(int a, int b);

}

//class Addition implements Operation{ //interface e zatoa implements
//    @Override
//    public int execute(int a, int b) {
//        return 0;
//    }


public class LambdaIntro {
    public static void main(String[] args) {
        //bez da definirame posebni klasi i za sobiranje, odzemanje, mnozenje...
        //KREIRAM 3 OBJEKTI

        Operation addition = new Operation() { //ANONIMNA KLASA
            @Override
            public int execute(int a, int b) {
                return a+b;
            }
        };

        Operation subtraction = new Operation() {
            @Override
            public int execute(int a, int b) {
                return a-b;
            }
        };

        Operation multiplication = (a, b) -> a*b; //klikame samo replace with lambda na new Operation

        //LAMBDA EXPRESSION  -  levo od strelkata se vleznite raboti od interfaceot - a, b
        Operation division = (a,b) ->  a / b;
                //desno od strelkata e output - return na nasata funkcija  - sto sakame da vrati

        int a =5, b= 8;
        System.out.println(addition.execute(a,b));
        System.out.println(subtraction.execute(a,b));
        System.out.println(multiplication.execute(a,b));
        System.out.println(division.execute(a,b));
    }
}


