package Banka;

import java.util.*;

public class Bank{

    List<Account> accountList; //Во Bank чува листа од сите видови сметки

//    List<Account> accountList = new ArrayList<Account>();

    public Bank(){
        accountList = new ArrayList<Account>();
    }

    public double totalAssets(){ //метод totalAssets кој ја враќа сумата на состојбата на сите сметки.
        double sum = 0;

        for(Account accounts:accountList){
            sum += accounts.getBalance();
        }
        return sum;
    }

    public double totalAssetsStream(){
        return accountList.stream().mapToDouble(accountList -> accountList.getBalance()).sum();
        /*
         so accountList. pristapuvam do elementite na nizata (site smetki), pa odam stream() ,
         pa mapToDouble ( od toa sto sum dobil MAPIRAJ VO NEKOJA DOUBLE VREDNOST) , i za sekoj account ke stane
         account.getBalance() i na kraj zemi ja nivnata suma imame stream od double vrednosti
         */
    }

    public void addAccounts(Account account){
        accountList.add(account);  //dodavame account vo accountList
    }

    public void addInterest(){ //содржи метод addInterest кој го повикува методот addInterest на сите сметки кои се подложни на камата. Метод за додавање на камата на сите сметки кои се подложни на камата

//        for(Account account : accountList){
//            if(account instanceof InterestBearingAccount){
//                ((InterestBearingAccount) account).addInterest(); //povikaj go metodot addInterest na site sto se podlozni na kamata
//            }
//        }

        /*
          The instanceof operator is used in the addInterest method to check whether each account in the list (accounts)
          is an instance of a class that implements the InterestBearingAccount interface.
          This check is performed to ensure that only those accounts which are eligible to receive interest are processed.
        */


        //MOZE I VAKA - 2RA OPCIJA
//        for(Account a: accountList){
//            if(a instanceof InterestBearingAccount){
//                InterestBearingAccount interestBearingAccount = (InterestBearingAccount) a;
//                interestBearingAccount.addInterest();
//            }
//        }


        //3TA OPCIJA - ANA
        for(Account accounts : accountList){
            if(accounts.getAccountType() == AccountType.INTEREST){
                ((InterestBearingAccount) accounts).addInterest();
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(Account account:accountList){
            stringBuilder.append(account.toString()).append("\n"); //i append pak za da dodademe nov red
        }
        return stringBuilder.toString();


        //RESENIE SO STREAMS
//         accountList.stream().forEach(account -> stringBuilder.append(account.toString()).append("\n"));  //  za sekoj 1 account napravi mi stringbuilder
//         return stringBuilder.toString();
    }

    /*
    VO VAKOV FORMAT KE BIDE DADENO
        AT(IMETO)(parts[0]) 1000.78(ID ACC.vrednosta)(parts[1])
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Bank bank = new Bank();
        Random random = new Random();

        //citam linija po linija
        int numAccounts = Integer.parseInt(scanner.nextLine()); //najdobro e da se cita preku nextLine i da parsirame

        for(int i=0;i<numAccounts;i++){
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+"); //odvojuvam vo posebni zborovi - split pravam na linijata

            int type = random.nextInt(3); //na 3 dela go deli
            if(type == 0){ //dodavame za sekoj Account posebno                  nie go parsirame vo Double
                bank.addAccounts(new InterestCheckingAccount(parts[0], Double.parseDouble(parts[1]))); //dodavam nov InterestCheckingAccount so parametrite (accountHolder --> parts[0] i balance --> parts[1]). Vo ovie 2 dela gi cuvame parts-ot, delovite podeleni
            }
            else if(type == 1){
                bank.addAccounts(new PlatinumCheckingAccount(parts[0], Double.parseDouble(parts[1])));
            }
            else {
                bank.addAccounts(new NonInterestCheckingAccount(parts[0], Double.parseDouble(parts[1])));
            }

            System.out.println("TEST BANK AND TOTAL ASSETS");
            System.out.println(bank);
            System.out.println(bank.totalAssets());


            System.out.println("TEST ADD INTEREST");
            bank.addInterest();
            System.out.println(bank);
            System.out.println(bank.totalAssets());
        }
    }

}