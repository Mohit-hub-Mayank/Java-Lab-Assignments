import java.util.*;

// Base Class
class Account {
    protected int accountNumber;
    protected double balance;

    public Account(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void display() {
        System.out.println("Account No: " + accountNumber + ", Balance: " + balance);
    }
}

// SavingsAccount Class
class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(int accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amount) {
        double interest = amount * interestRate / 100;
        balance += amount + interest;
        System.out.println("Deposited with interest: " + (amount + interest));
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Savings withdrawal: " + amount);
        } else {
            System.out.println("Insufficient funds in Savings Account!");
        }
    }

    @Override
    public void display() {
        System.out.println("Savings Account -> Acc No: " + accountNumber +
                ", Balance: " + balance +
                ", Interest Rate: " + interestRate + "%");
    }
}

// LoanAccount Class
class LoanAccount extends Account {
    private double loanLimit;

    public LoanAccount(int accountNumber, double balance, double loanLimit) {
        super(accountNumber, balance);
        this.loanLimit = loanLimit;
    }

    @Override
    public void deposit(double amount) {
        balance -= amount; // paying loan
        System.out.println("Loan repaid: " + amount);
    }

    @Override
    public void withdraw(double amount) {
        if (balance + amount <= loanLimit) {
            balance += amount;
            System.out.println("Loan taken: " + amount);
        } else {
            System.out.println("Loan limit exceeded!");
        }
    }

    @Override
    public void display() {
        System.out.println("Loan Account -> Acc No: " + accountNumber +
                ", Loan Used: " + balance +
                ", Limit: " + loanLimit);
    }
}

// Customer Class
class Customer {
    private int customerId;
    private String name;
    private List<Account> accounts;

    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account acc) {
        accounts.add(acc);
    }

    public void displayCustomerInfo() {
        System.out.println("\nCustomer ID: " + customerId + ", Name: " + name);
        System.out.println("Accounts:");
        for (Account acc : accounts) {
            acc.display();
        }
    }
}

// Main Class
public class BankingApp {
    public static void main(String[] args) {

        // Create customers
        Customer c1 = new Customer(1, "Mohit");
        Customer c2 = new Customer(2, "Rahul");

        // Create accounts
        SavingsAccount sa1 = new SavingsAccount(101, 5000, 5);
        LoanAccount la1 = new LoanAccount(201, 2000, 10000);

        SavingsAccount sa2 = new SavingsAccount(102, 8000, 4);
        LoanAccount la2 = new LoanAccount(202, 1000, 5000);

        // Perform operations
        sa1.deposit(1000);
        sa1.withdraw(200);

        la1.withdraw(3000);
        la1.deposit(1000);

        // Assign accounts to customers
        c1.addAccount(sa1);
        c1.addAccount(la1);

        c2.addAccount(sa2);
        c2.addAccount(la2);

        // Store customers in ArrayList
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(c1);
        customers.add(c2);

        // Display all customer info
        System.out.println("\n===== CUSTOMER DETAILS =====");
        for (Customer c : customers) {
            c.displayCustomerInfo();
        }
    }
}
