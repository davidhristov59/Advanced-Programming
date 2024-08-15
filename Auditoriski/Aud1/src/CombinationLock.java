public class CombinationLock {

    private int combination;
    private boolean isOpen;

    public CombinationLock(int combination) {
        this.combination = combination;
        this.isOpen = false; //zakluceno e
    }

    //go otvorame katanecot
    public boolean open(int combination){
        this.isOpen = (this.combination == combination);
        return this.isOpen;
    }

    public boolean changeCombo(int oldCombination, int newCombination){

        boolean isCorrect = (this.combination == oldCombination);

        if(isCorrect){
            this.combination = newCombination;
        }

        return isCorrect;
    }

    public void lock(){
        this.isOpen = false;
    }


    public static void main(String[] args) {

        CombinationLock combinationLock = new CombinationLock(313); //tocna kombinacija

        System.out.println("TEST IS OPEN");
        System.out.println(combinationLock.isOpen);

        System.out.println("Test OPEN");
        System.out.println(combinationLock.open(525));
        System.out.println(combinationLock.open(603));
        System.out.println(combinationLock.open(313));

        combinationLock.lock();

        System.out.println(combinationLock.changeCombo(313,523));
        System.out.println(combinationLock.open(523));



    }

}
