import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static Bank bank = new Bank();

    static Admin admin = new Admin("admin", "admin123");

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n================================");
            System.out.println("       ADVANCED JAVA BANK");
            System.out.println("================================");

            System.out.println("1. Create Account");
            System.out.println("2. Customer Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Apply For Loan");
            System.out.println("5. View Loans");
            System.out.println("6. Create Fixed Deposit");
            System.out.println("7. View Fixed Deposits");
            System.out.println("0. Exit");

            System.out.print("Enter Choice: ");

            choice = readInt();

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    customerLogin();
                    break;

                case 3:
                    adminLogin();
                    break;

                case 4:
                    applyLoan();
                    break;

                case 5:
                    bank.displayLoans();
                    break;

                case 6:
                    createFD();
                    break;

                case 7:
                    bank.displayFDs();
                    break;

                case 0:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }

    static void createAccount() {

        System.out.println("\n===== ACCOUNT REGISTRATION =====");

        System.out.print("Account Number: ");
        int number = readInt();

        System.out.print("Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Phone: ");
        String phone = sc.nextLine();

        System.out.print("Address: ");
        String address = sc.nextLine();

        System.out.println("1. Savings");
        System.out.println("2. Current");
        System.out.println("3. Salary");
        System.out.println("4. Business");

        System.out.print("Account Type: ");

        int typeChoice = readInt();

        if (typeChoice < 1 || typeChoice > 4) {
            System.out.println("Invalid account type!");
            return;
        }

        AccountType type = AccountType.values()[typeChoice - 1];

        System.out.print("Create PIN: ");
        String pin = sc.nextLine();

        System.out.print("Initial Balance: ");
        double balance = readDouble();

        bank.createAccount(
                number,
                name,
                email,
                phone,
                address,
                type,
                pin,
                balance
        );
    }

    static BankAccount customerLogin() {

        System.out.println("\n===== CUSTOMER LOGIN =====");

        System.out.print("Account Number: ");
        int number = readInt();

        BankAccount account = bank.findAccount(number);

        if (account == null) {
            System.out.println("Account not found!");
            return null;
        }

        if (!account.isActive()) {
            System.out.println("Account is blocked!");
            return null;
        }

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        if (!account.verifyPin(pin)) {
            System.out.println("Wrong PIN!");
            return null;
        }

        System.out.println("Login successful!");

        customerMenu(account);

        return account;
    }

    static void customerMenu(BankAccount account) {

        int choice;

        do {

            System.out.println("\n===== CUSTOMER DASHBOARD =====");

            System.out.println("Welcome " +
                    account.getCustomer().getName());

            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Check Balance");
            System.out.println("5. Account Details");
            System.out.println("6. Transaction History");
            System.out.println("7. Change PIN");
            System.out.println("8. Update Email");
            System.out.println("9. Update Phone");
            System.out.println("0. Logout");

            System.out.print("Enter Choice: ");

            choice = readInt();

            switch (choice) {

                case 1:
                    System.out.print("Amount: ");
                    account.deposit(readDouble());
                    break;

                case 2:
                    System.out.print("Amount: ");
                    account.withdraw(readDouble());
                    break;

                case 3:
                    transfer(account);
                    break;

                case 4:
                    System.out.println("Balance: Rs. " +
                            account.getBalance());
                    break;

                case 5:
                    account.displayAccount();
                    account.getCustomer().displayProfile();
                    break;

                case 6:
                    account.showTransactions();
                    break;

                case 7:
                    changePin(account);
                    break;

                case 8:
                    System.out.print("New Email: ");
                    String email = sc.nextLine();

                    if (email.matches(
                            "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                        account.getCustomer().updateEmail(email);
                        System.out.println("Email updated!");
                    } else {
                        System.out.println("Invalid email!");
                    }
                    break;

                case 9:
                    System.out.print("New Phone: ");
                    String phone = sc.nextLine();

                    if (phone.matches("\\d{10}")) {
                        account.getCustomer().updatePhone(phone);
                        System.out.println("Phone updated!");
                    } else {
                        System.out.println("Invalid phone!");
                    }
                    break;

                case 0:
                    System.out.println("Logged out!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }

    static void transfer(BankAccount sender) {

        System.out.print("Receiver Account Number: ");

        int number = readInt();

        BankAccount receiver = bank.findAccount(number);

        if (receiver == null) {
            System.out.println("Receiver not found!");
            return;
        }

        System.out.print("Transfer Amount: ");

        double amount = readDouble();

        sender.transfer(receiver, amount);
    }

    static void changePin(BankAccount account) {

        System.out.print("Old PIN: ");
        String oldPin = sc.nextLine();

        System.out.print("New PIN: ");
        String newPin = sc.nextLine();

        account.changePin(oldPin, newPin);
    }

    static void adminLogin() {

        System.out.println("\n===== ADMIN LOGIN =====");

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        if (admin.login(username, password)) {

            System.out.println("Admin login successful!");

            adminMenu();

        } else {
            System.out.println("Invalid admin credentials!");
        }
    }

    static void adminMenu() {

        int choice;

        do {

            admin.displayAdminMenu();

            System.out.println("6. View Total Bank Balance");
            System.out.println("7. View Loans");
            System.out.println("8. Approve Loan");
            System.out.println("9. Reject Loan");

            System.out.print("Enter Choice: ");

            choice = readInt();

            switch (choice) {

                case 1:
                    bank.displayAllAccounts();
                    break;

                case 2:
                    System.out.print("Account Number: ");
                    bank.searchAccount(readInt());
                    break;

                case 3:
                    System.out.print("Account Number: ");
                    bank.blockAccount(readInt());
                    break;

                case 4:
                    System.out.print("Account Number: ");
                    bank.activateAccount(readInt());
                    break;

                case 5:
                    System.out.println("Total Accounts: " +
                            bank.getTotalAccounts());
                    break;

                case 6:
                    System.out.println("Total Bank Balance: Rs. " +
                            bank.getTotalBalance());
                    break;

                case 7:
                    bank.displayLoans();
                    break;

                case 8:
                    System.out.print("Loan ID: ");
                    bank.updateLoanStatus(readInt(), true);
                    break;

                case 9:
                    System.out.print("Loan ID: ");
                    bank.updateLoanStatus(readInt(), false);
                    break;

                case 0:
                    System.out.println("Admin logged out!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }

    static void applyLoan() {

        System.out.println("\n===== LOAN APPLICATION =====");

        System.out.print("Account Number: ");
        int accountNumber = readInt();

        BankAccount account = bank.findAccount(accountNumber);

        if (account == null || !account.isActive()) {
            System.out.println("Invalid or inactive account!");
            return;
        }

        System.out.print("Loan Amount: ");
        double amount = readDouble();

        System.out.print("Annual Interest Rate (%): ");
        double rate = readDouble();

        System.out.print("Duration in Months: ");
        int months = readInt();

        bank.applyForLoan(
                accountNumber,
                amount,
                rate,
                months
        );
    }

    static void createFD() {

        System.out.println("\n===== FIXED DEPOSIT =====");

        System.out.print("Amount: ");
        double amount = readDouble();

        System.out.print("Interest Rate: ");
        double rate = readDouble();

        System.out.print("Duration in Years: ");
        int years = readInt();

        bank.createFD(amount, rate, years);
    }

    static int readInt() {

        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid integer: ");
            sc.next();
        }

        int value = sc.nextInt();
        sc.nextLine();

        return value;
    }

    static double readDouble() {

        while (!sc.hasNextDouble()) {
            System.out.print("Enter a valid amount: ");
            sc.next();
        }

        double value = sc.nextDouble();
        sc.nextLine();

        return value;
    }
}