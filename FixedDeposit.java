import java.time.LocalDate;

public class FixedDeposit {

    private int fdNumber;
    private double principal;
    private double interestRate;
    private int durationYears;
    private LocalDate startDate;

    public FixedDeposit(int fdNumber, double principal,
                        double interestRate, int durationYears) {

        this.fdNumber = fdNumber;
        this.principal = principal;
        this.interestRate = interestRate;
        this.durationYears = durationYears;
        this.startDate = LocalDate.now();
    }

    public double calculateMaturityAmount() {

        return principal *
                Math.pow(1 + interestRate / 100, durationYears);
    }

    public void displayFD() {

        System.out.println("\n===== FIXED DEPOSIT =====");

        System.out.println("FD Number: " + fdNumber);
        System.out.println("Principal: Rs. " + principal);
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Duration: " + durationYears + " years");
        System.out.println("Maturity Amount: Rs. " +
                calculateMaturityAmount());
        System.out.println("Start Date: " + startDate);
    }
}