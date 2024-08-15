package Banka;

//public abstract class Account {
//
//    private String accountHolder;
//    private static long ID = 1L;
//    private long accountID;
//    private double balance;
//    private AccountType accountType;
//
//    public Account(String accountHolder, double balance) {
//        this.accountHolder = accountHolder;
//        this.balance = balance;
//        this.accountID = ID++;     //da se zgolemuva sekvencijalno
//    }
//
//    public String getAccountHolder() {
//        return accountHolder;
//    }
//
//
//    public long getAccountID() {
//        return accountID;
//    }
//
//    public double getBalance() {
//        return balance;
//    }
//
//    public void deposit(double amount) {
//        this.balance += amount;
//    }
//
//    public void withdraw(double amount){
//        this.balance -= amount;
//    }
//
//    public AccountType getAccountType() {
//        return accountType;
//    }
//}


public abstract class Account{

    private String accountHolder;
    private long accountID;
    private double balance;
    private AccountType accountType;
    private static long ID = 1L; //sekvencijalen broj na smetkite

    public Account(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.accountID = ID++; //sekvencijalen broj na smetkite - се инкрементира
        /*
        Во вашата прашање, секвенцијалниот број се користи за да се идентификуваат сметките. Секој пат кога се
        создава нова сметка, се доделува уникален број на таа сметка. Примерот во кодот кој го објавив покажува како
        секвенцијалниот број (претставен со променливата "accountNumber") се инкрементира со секоја нова сметка.
        По првата сметка, "accountNumber" ќе биде 1000, по втората 1001, и така натаму. (ako pocne od 1000). Во нашиот случај почнуваме од 1 и се инкрементира ...
        Ова е корисно за да се овозможи лесна идентификација на секоја сметка и да се осигура дека секоја има уникатен идентификатор.
         */
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public long getAccountID() {
        return accountID;
    }

    public double getBalance() { //Pristap do sostojbata
        return balance;
    }

    public void deposit(double amount){
        this.balance += amount;
    }

    public void withdraw(double amount){
        this.balance -= amount;
    }

    public abstract AccountType getAccountType();//ke bide abstract


    @Override
    public String toString() {
        return String.format("%s %d %.2f", accountHolder,accountID, balance);
    }
}