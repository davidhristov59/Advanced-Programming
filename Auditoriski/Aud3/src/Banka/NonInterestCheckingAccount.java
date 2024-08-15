package Banka;

public class NonInterestCheckingAccount extends Account{

    public NonInterestCheckingAccount(String accountHolder, double balance) {
        super(accountHolder, balance);
    }

    //не е InterestBearingAccount што значи не мора да го имплементираме методот

    @Override
    public AccountType getAccountType() {
        return AccountType.NON_INTEREST;
    }

    @Override
    public String toString() {
        return super.toString() + " " + getAccountType();
    }

}