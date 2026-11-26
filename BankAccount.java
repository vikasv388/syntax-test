import java.util.ArrayList;

public class BankAccount {

    private int accountNumber;
    private Customer customer;
    private AccountType accountType;

    private String pin;
    private double balance;

    private boolean active;

    private ArrayList<Transaction> transactions;

    private static int transactionCounter = 1000;

    public BankAccount(int accountNumber,
                       Customer customer,
                       AccountType accountType,
                       String pin,
                       double balance) {

        this.accountNumber = accountNumber;
        this.customer = customer;
        this.accountType = accountType;
        this.pin = pin;
        this.balance = balance;

        this.active = true;

        transactions = new ArrayList<>();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    public boolean verifyPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public void changePin(String oldPin, String newPin) {

        if (!verifyPin(oldPin)) {
            System.out.println("Incorrect old PIN!");
            return;
        }

        if (newPin == null || !newPin.matches("\\d{4}")) {
            System.out.println("PIN must contain 4 digits!");
            return;
        }

        pin = newPin;

        System.out.println("PIN changed successfully!");
    }

    public void deposit(double amount) {

        if (!active) {
            System.out.println("Account is blocked!");
            return;
        }

        if (amount <= 0 || !Double.isFinite(amount)) {
            System.out.println("Invalid amount!");
            return;
        }

        balance += amount;

        addTransaction(
                TransactionType.DEPOSIT,
                amount,
                "Money deposited"
        );

        System.out.println("Deposit successful!");
    }

    public void withdraw(double amount) {

        if (!active) {
            System.out.println("Account is blocked!");
            return;
        }

        if (amount <= 0 || !Double.isFinite(amount)) {
            System.out.println("Invalid amount!");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient balance!");
            return;
        }

        balance -= amount;

        addTransaction(
                TransactionType.WITHDRAWAL,
                amount,
                "Money withdrawn"
        );

        System.out.println("Withdrawal successful!");
    }

    public void transfer(BankAccount receiver, double amount) {

        if (receiver == null || receiver == this) {
            System.out.println("Invalid receiver!");
            return;
        }

        if (!active || !receiver.active) {
            System.out.println("One of the accounts is blocked!");
            return;
        }

        if (amount <= 0 || !Double.isFinite(amount)) {
            System.out.println("Invalid amount!");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient balance!");
            return;
        }

        balance -= amount;
        receiver.balance += amount;

        addTransaction(
                TransactionType.TRANSFER_SENT,
                amount,
                "Transferred to " + receiver.accountNumber
        );

        receiver.addTransaction(
                TransactionType.TRANSFER_RECEIVED,
                amount,
                "Received from " + accountNumber
        );

        System.out.println("Transfer completed!");
    }

    private void addTransaction(TransactionType type,
                                double amount,
                                String description) {

        transactions.add(
                new Transaction(
                        ++transactionCounter,
                        type,
                        amount,
                        description
                )
        );
    }

    public void showTransactions() {

        System.out.println("\n===== TRANSACTION HISTORY =====");

        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        for (Transaction transaction : transactions) {
            transaction.display();
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void displayAccount() {

        System.out.println("\n===== ACCOUNT DETAILS =====");

        System.out.println("Account Number: " + accountNumber);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: Rs. " + balance);
        System.out.println("Status: " +
                (active ? "Active" : "Blocked"));
    }
}