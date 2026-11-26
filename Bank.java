import java.util.ArrayList;

public class Bank {

    private ArrayList<BankAccount> accounts;
    private ArrayList<Loan> loans;
    private ArrayList<FixedDeposit> deposits;

    private int nextCustomerId = 100;
    private int nextLoanId = 500;
    private int nextFDId = 900;

    public Bank() {

        accounts = new ArrayList<>();
        loans = new ArrayList<>();
        deposits = new ArrayList<>();
    }

    public BankAccount findAccount(int accountNumber) {

        for (BankAccount account : accounts) {

            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }

        return null;
    }

    public void createAccount(int accountNumber,
                              String name,
                              String email,
                              String phone,
                              String address,
                              AccountType type,
                              String pin,
                              double initialBalance) {

        if (findAccount(accountNumber) != null) {
            System.out.println("Account already exists!");
            return;
        }

        if (name == null || name.isBlank() ||
                email == null || !email.matches(
                        "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            System.out.println("Invalid customer details!");
            return;
        }

        if (phone == null || !phone.matches("\\d{10}")) {
            System.out.println("Enter a valid 10-digit phone number!");
            return;
        }

        if (pin == null || !pin.matches("\\d{4}")) {
            System.out.println("PIN must be 4 digits!");
            return;
        }

        if (initialBalance < 0 ||
                !Double.isFinite(initialBalance)) {
            System.out.println("Invalid initial balance!");
            return;
        }

        Customer customer = new Customer(
                ++nextCustomerId,
                name,
                email,
                phone,
                address
        );

        BankAccount account = new BankAccount(
                accountNumber,
                customer,
                type,
                pin,
                initialBalance
        );

        accounts.add(account);

        System.out.println("Account created successfully!");
    }

    public void displayAllAccounts() {

        for (BankAccount account : accounts) {
            account.displayAccount();
        }
    }

    public void blockAccount(int accountNumber) {

        BankAccount account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        account.setActive(false);

        System.out.println("Account blocked successfully!");
    }

    public void activateAccount(int accountNumber) {

        BankAccount account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        account.setActive(true);

        System.out.println("Account activated successfully!");
    }

    public void searchAccount(int accountNumber) {

        BankAccount account = findAccount(accountNumber);

        if (account != null) {
            account.displayAccount();
        } else {
            System.out.println("Account not found!");
        }
    }

    public void applyForLoan(int accountNumber,
                             double amount,
                             double interest,
                             int months) {

        if (findAccount(accountNumber) == null ||
                amount <= 0 || !Double.isFinite(amount) ||
                interest < 0 || !Double.isFinite(interest) ||
                months <= 0) {

            System.out.println("Invalid loan application!");
            return;
        }

        Loan loan = new Loan(
                ++nextLoanId,
                accountNumber,
                amount,
                interest,
                months
        );

        loans.add(loan);

        System.out.println("Loan application submitted!");
    }

    public void displayLoans() {

        for (Loan loan : loans) {
            loan.displayLoan();
        }
    }

    public void updateLoanStatus(int loanId, boolean approved) {

        for (Loan loan : loans) {

            if (loan.getLoanId() == loanId) {

                if (!loan.getStatus().equals("PENDING")) {
                    System.out.println("Loan already processed!");
                    return;
                }

                if (approved) {
                    loan.approve();
                } else {
                    loan.reject();
                }

                System.out.println("Loan status updated!");
                return;
            }
        }

        System.out.println("Loan not found!");
    }

    public void createFD(double amount, double rate, int years) {

        if (amount <= 0 || !Double.isFinite(amount) ||
                rate < 0 || !Double.isFinite(rate) ||
                years <= 0) {

            System.out.println("Invalid FD details!");
            return;
        }

        FixedDeposit fd = new FixedDeposit(
                ++nextFDId,
                amount,
                rate,
                years
        );

        deposits.add(fd);

        fd.displayFD();
    }

    public void displayFDs() {

        for (FixedDeposit fd : deposits) {
            fd.displayFD();
        }
    }

    public int getTotalAccounts() {
        return accounts.size();
    }

    public double getTotalBalance() {

        double total = 0;

        for (BankAccount account : accounts) {
            total += account.getBalance();
        }

        return total;
    }
}