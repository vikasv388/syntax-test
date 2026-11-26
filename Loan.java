import java.time.LocalDate;

public class Loan {

    private int loanId;
    private int accountNumber;
    private double loanAmount;
    private double interestRate;
    private int durationMonths;

    private String status;
    private LocalDate applicationDate;

    public Loan(int loanId, int accountNumber,
                double loanAmount, double interestRate,
                int durationMonths) {

        this.loanId = loanId;
        this.accountNumber = accountNumber;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.durationMonths = durationMonths;

        this.status = "PENDING";
        this.applicationDate = LocalDate.now();
    }

    public int getLoanId() {
        return loanId;
    }

    public String getStatus() {
        return status;
    }

    public void approve() {
        status = "APPROVED";
    }

    public void reject() {
        status = "REJECTED";
    }

    public double calculateTotalAmount() {

        double monthlyRate = interestRate / 1200;

        if (monthlyRate == 0) {
            return loanAmount;
        }

        double emi = loanAmount * monthlyRate *
                Math.pow(1 + monthlyRate, durationMonths) /
                (Math.pow(1 + monthlyRate, durationMonths) - 1);

        return emi * durationMonths;
    }

    public void displayLoan() {

        System.out.println("\n===== LOAN DETAILS =====");

        System.out.println("Loan ID: " + loanId);
        System.out.println("Account: " + accountNumber);
        System.out.println("Loan Amount: Rs. " + loanAmount);
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Duration: " + durationMonths + " months");
        System.out.println("Estimated Repayment: Rs. " +
                calculateTotalAmount());
        System.out.println("Status: " + status);
        System.out.println("Application Date: " + applicationDate);
    }
}