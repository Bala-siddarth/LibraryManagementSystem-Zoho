package domains;


import java.time.LocalDate;

public class BorrowingRecord {

    private String borrowingId;
    private String memberId;
    private String bookId;
    private LocalDate borrowingDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private BorrowingStatus status;

    public BorrowingRecord(String borrowingId, String memberId, String bookId, LocalDate borrowingDate, LocalDate dueDate) {
        if (borrowingId == null || borrowingId.trim().isEmpty()) {
            throw new IllegalArgumentException("Borrowing ID cannot be empty.");
        }

        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be empty.");
        }

        if (bookId == null || bookId.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be empty.");
        }

        if (borrowingDate == null || dueDate == null) {
            throw new IllegalArgumentException("Borrowing date and due date cannot be null!!!.");
        }

        this.borrowingId = borrowingId.trim();
        this.memberId = memberId.trim();
        this.bookId = bookId.trim();
        this.borrowingDate = borrowingDate;
        this.dueDate = dueDate;
        this.returnDate = null;
        this.status = BorrowingStatus.ACTIVE;
    }

    public String getBorrowingId() {
        return borrowingId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public BorrowingStatus getStatus() {
        return status;
    }

    public void markAsReturned(LocalDate returnDate) {

        if (this.status == BorrowingStatus.RETURNED) {
            throw new IllegalArgumentException("This book has already been returned.");
        }

        if (returnDate == null) {
            throw new IllegalArgumentException("Return date cannot be null !!!.");
        }

        if (returnDate.isBefore(borrowingDate)) {
            throw new IllegalArgumentException("Return date cannot be before borrowing date !!!.");
        }

        this.returnDate = returnDate;
        this.status = BorrowingStatus.RETURNED;
    }
}

