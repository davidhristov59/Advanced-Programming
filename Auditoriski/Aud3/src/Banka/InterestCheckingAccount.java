package Banka;

public class InterestCheckingAccount extends Account implements InterestBearingAccount{ //NASLEDUVA(extends) OD ACCOUNT A GO IMPLEMENTIRA(implements) METODOT OD InterestBearingAccount

    public static final double INTEREST_RATE = 0.03; //3% kamata
    //public za da imam pristap
    public InterestCheckingAccount(String accountHolder, double balance) {
        super(accountHolder, balance);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.INTEREST;
    }

    @Override
    public void addInterest() { //Метод за зголемување на состојбата со 3% камата

        super.deposit(super.getBalance() * INTEREST_RATE); //vise go koristam deposit methodot za da ja zgolema sostojbata so 3% kamata

        //MOZE I VAKA
//        double interest = getBalance() * INTEREST_RATE;
//        deposit(interest);

    }

    @Override
    public String toString() {
        return super.toString() + " " + getAccountType();
    //preku super go zimame toString od drugata klasa + dodavame da go pecati tipot
    }


}