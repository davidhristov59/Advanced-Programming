package Banka;

public class PlatinumCheckingAccount extends InterestCheckingAccount{ //nasleduva od InterestCheckingAccount

    public PlatinumCheckingAccount(String accountHolder, double balance) {
        super(accountHolder, balance);
    }

    public void addInterest(){
        super.deposit(getBalance() * INTEREST_RATE * 2); //dupla kamata
            //                      ја зголемува состојбата двојно од каматата
    }

}