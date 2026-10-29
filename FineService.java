package services;

import domains.BorrowingRecord;
import domains.Fine;
import domains.PaymentStatus;
import memory.InMemoryDataStore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class FineService {

    private final InMemoryDataStore dataStore;

    public FineService(InMemoryDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void createFine(Fine fine) {
        if (fine == null) {
            throw new IllegalArgumentException("Fine cannot be null !!!.");
        }
        boolean borrowingExists = dataStore.getBorrowingRecords().containsKey(fine.getBorrowingId());
        if (!borrowingExists) {
            throw new IllegalArgumentException("Borrowing record does not exist.");
        }
        for (Fine existingFine : dataStore.getFines().values()) {
            if (existingFine.getBorrowingId().equals(fine.getBorrowingId())) {
                throw new IllegalArgumentException("A fine already exists for this borrowing record!!!.");
            }
        }
        dataStore.getFines().put(fine.getFineId(), fine);
        System.out.println("Fine has been created!!!");
    }

    public double calculateFineAmount(LocalDate dueDate, LocalDate returnDate) {
        if (dueDate == null || returnDate == null) {
            throw new IllegalArgumentException("Due date and return date cannot be null.");
        }
        if (returnDate.isBefore(dueDate)) {
            return 0.0;
        }
        long delayDays = ChronoUnit.DAYS.between(dueDate, returnDate);
        double finePerDay = 10.0;
        return delayDays * finePerDay;
    }

    public void payFine(String fineId) {
        if (fineId == null || fineId.trim().isEmpty()) {
            throw new IllegalArgumentException("Fine ID cannot be empty.");
        }
        Fine fine = dataStore.getFines().get(fineId.toUpperCase().trim());
        if (fine == null) {
            throw new IllegalStateException("Fine doesn't exists!!!");
        }
        if (fine.getStatus() == PaymentStatus.PAID) {
            throw new IllegalStateException("Fine already paid.");
        }
        fine.markAsPaid();
    }

    public List<Fine> getFinesByMemberId(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be empty.");
        }

        if (!dataStore.getMembers().containsKey(memberId.toUpperCase().trim())) {
            throw new IllegalStateException("Member not found!!!.");
        }
        List<Fine> memberFines = new ArrayList<>();
        for (Fine fine : dataStore.getFines().values()) {
            BorrowingRecord record = dataStore.getBorrowingRecords().get(fine.getBorrowingId());
            if (record != null && record.getMemberId().equals(memberId.toUpperCase().trim()) && fine.getStatus() == PaymentStatus.UNPAID) {
                memberFines.add(fine);
            }
        }
        return memberFines;
    }
}
