import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class InvalidPickerArguments extends Exception{

    public InvalidPickerArguments(String message) {
        super(message);
    }
}
class FinalistPicker{

    int finalists;

    static Random RANDOM = new Random(); //slucajni broevi generira

    public FinalistPicker(int finalists) {
        this.finalists = finalists;
    }

    public List<Integer> pick(int n) throws InvalidPickerArguments{ //n < 30 i n <= 0

        if(n > finalists || n <= 0){
            throw new InvalidPickerArguments("The number n is invalid");
        }

        List<Integer> pickedFinalists = new ArrayList<>();

        while(pickedFinalists.size() != n){ //se dodeka ne ja napolnime so n finalisti
            int pick = RANDOM.nextInt(finalists)+1; //mora granica da stavime vo nextInt. Range-ot ke bide od 1 do 30. So +1 go shiftame do granicata >=
            //nextInt generira od 0 do brojot pred granicata(bez granicata)
            //vo slucajot ako treba od 0-30 on ke generira od 0-29

            if(!pickedFinalists.contains(pick)){ //ako ne go sodrzi vo listata, dodadi go
                pickedFinalists.add(pick);
            }
        }
        return pickedFinalists;
    }
}

public class FinalistTest {
    public static void main(String[] args) {

        FinalistPicker finalistPicker = new FinalistPicker(30);
        try {
            System.out.println(finalistPicker.pick(3)); //ke izberam 3 od dadenite
        } catch (InvalidPickerArguments e) {
            System.out.println(e.getMessage());
        }
    }
}

