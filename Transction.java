import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private int transactionId;
    private TransactionType type;
    private double amount;
    private String description;
    private LocalDateTime date;

    public Transaction(int transactionId,
                       TransactionType type,
                       double amount,
                       String description) {

        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = LocalDateTime.now();
    }

    public int getTransactionId() {
        return transactionId;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public void display() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("--------------------------------");
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Type: " + type);
        System.out.println("Amount: Rs. " + amount);
        System.out.println("Description: " + description);
        System.out.println("Date: " + date.format(formatter));
    }
}