package domains;

import domains.PaymentStatus;
import java.time.LocalDate;

public class Fine {

    private String fineId;
    private String borrowingId;
    private double fineAmount;
    private PaymentStatus status;


    public Fine(String fineId, String borrowingId, double fineAmount) {
        if (fineId == null || fineId.trim().isEmpty()) {
            throw new IllegalArgumentException("Fine ID cannot be empty!!!.");
        }
        if (borrowingId == null || borrowingId.trim().isEmpty()) {
            throw new IllegalArgumentException("Borrowing ID cannot be empty!!!.");
        }
        this.fineId = fineId.trim();
        this.borrowingId = borrowingId.trim();
        setFineAmount(fineAmount);
        this.status = PaymentStatus.UNPAID;
    }

    public String getFineId() {
        return fineId;
    }

    public String getBorrowingId() {
        return borrowingId;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setFineAmount(double fineAmount) {
        if (!Double.isFinite(fineAmount) || fineAmount <= 0) {
            throw new IllegalArgumentException("Fine amount must be a valid positive amount!!!.");
        }
        this.fineAmount = fineAmount;
    }

    public void markAsPaid() {
        if (this.status == PaymentStatus.PAID) {
            throw new IllegalStateException("Fine has already been paid!!!.");
        }
        this.status = PaymentStatus.PAID;
    }
}

