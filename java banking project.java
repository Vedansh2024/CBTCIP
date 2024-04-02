import java.util.Scanner;

class BankDetails {
    private String accno;
    private String name;
    private String acc_type;
    private long balance;
    Scanner sc = new Scanner(System.in);

    public void openAccount() {
        System.out.print("Enter Account No: ");
        accno = sc.next();
        System.out.print("Enter Account type: ");
        acc_type = sc.next();
        System.out.print("Enter Name: ");
        name = sc.next();
        System.out.print("Enter Balance: ");
        balance = sc.nextLong();
    }

    public void showAccount() {
        System.out.println("Name of account holder: " + name);
        System.out.println("Account no.: " + accno);
        System.out.println("Account type: " + acc_type);
        System.out.println("Balance: " + balance);
    }

    public void deposit() {
        long amt;
        System.out.println("Enter the amount you want to deposit: ");
        amt = sc.nextLong();
        balance = balance + amt;
        System.out.println("Amount deposited successfully. Current balance: " + balance);
    }

    public void withdrawal() {
        long amt;
        System.out.println("Enter the amount you want to withdraw: ");
        amt = sc.nextLong();
        if (balance >= amt) {
            balance = balance - amt;
            System.out.println("Amount withdrawn successfully. Current balance: " + balance);
        } else {
            System.out.println("Your balance is less than " + amt + "\tTransaction failed...!!");
        }
    }

    public boolean search(String ac_no) {
        if (accno.equals(ac_no)) {
            showAccount();
            return (true);
        }
        return (false);
    }

    public void transferFunds(BankDetails receiver) {
        System.out.print("Enter the amount you want to transfer: ");
        long amount = sc.nextLong();
        if (balance >= amount) {
            balance -= amount;
            receiver.balance += amount;
            System.out.println("Amount transferred successfully. Current balance: " + balance);
        } else {
            System.out.println("Insufficient balance for transfer.");
        }
    }
}

public class BankingApp {
    public static void main(String arg[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many number of customers do you want to input? ");
        int n = sc.nextInt();
        BankDetails C[] = new BankDetails[n];
        for (int i = 0; i < C.length; i++) {
            C[i] = new BankDetails();
            C[i].openAccount();
        }
        int ch;
        do {
            System.out.println("\n **Banking System Application");
            System.out.println("1. Display all account details\n2. Search by Account number\n3. Deposit the amount\n4. Withdraw the amount\n5. Transfer funds\n6. Exit");
            System.out.println("Enter your choice: ");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    for (int i = 0; i < C.length; i++) {
                        C[i].showAccount();
                    }
                    break;
                case 2:
                    System.out.print("Enter account no. you want to search: ");
                    String ac_no = sc.next();
                    boolean found = false;
                    for (int i = 0; i < C.length; i++) {
                        found = C[i].search(ac_no);
                        if (found) {
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist..!!");
                    }
                    break;
                case 3:
                    System.out.print("Enter Account no. : ");
                    ac_no = sc.next();
                    found = false;
                    for (int i = 0; i < C.length; i++) {
                        found = C[i].search(ac_no);
                        if (found) {
                            C[i].deposit();
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist..!!");
                    }
                    break;
                case 4:
                    System.out.print("Enter Account No : ");
                    ac_no = sc.next();
                    found = false;
                    for (int i = 0; i < C.length; i++) {
                        found = C[i].search(ac_no);
                        if (found) {
                            C[i].withdrawal();
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist..!!");
                    }
                    break;
                case 5:
                    System.out.print("Enter your account no. : ");
                    String senderAccount = sc.next();
                    System.out.print("Enter receiver's account no. : ");
                    String receiverAccount = sc.next();
                    BankDetails sender = null;
                    BankDetails receiver = null;
                    for (int i = 0; i < C.length; i++) {
                        if (C[i].search(senderAccount)) {
                            sender = C[i];
                        }
                        if (C[i].search(receiverAccount)) {
                            receiver = C[i];
                        }
                    }
                    if (sender != null && receiver != null) {
                        sender.transferFunds(receiver);
                    } else {
                        System.out.println("Invalid sender or receiver account number.");
                    }
                    break;
                case 6:
                    System.out.println("See you soon...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
                    break;
            }
        }
        while (ch != 6);
    }
}
}