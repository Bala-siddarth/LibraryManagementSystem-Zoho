package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import domains.*;
import memory.InMemoryDataStore;

public class  BorrowingService {
    private final InMemoryDataStore dataStore;
    private final FineService fineService;
    public BorrowingService(InMemoryDataStore dataStore) {
        this.dataStore = dataStore;
        this.fineService = new FineService(dataStore);
    }

    private void checkEligibility(Member member, Book book, LocalDate borrowingDate) {
        int activeBorrowings = 0;
        for (BorrowingRecord record : dataStore.getBorrowingRecords().values()) {
            if (record.getMemberId().equals(member.getMemberId()) && record.getStatus() == BorrowingStatus.ACTIVE) {
                activeBorrowings++;
            }
        }
        if (activeBorrowings >= 3) {
            throw new IllegalStateException("Member cannot borrow more than 3 active books.");
        }
        for (BorrowingRecord record : dataStore.getBorrowingRecords().values()) {

            if (record.getMemberId().equals(member.getMemberId()) && record.getStatus() == BorrowingStatus.ACTIVE && record.getDueDate().isBefore(borrowingDate)) {
                throw new IllegalStateException("Memeber has an overdue book and cannot borrow another book.");
            }
        }
        for(Fine fine : dataStore.getFines().values()){
            if(fine.getStatus() == PaymentStatus.UNPAID){
                BorrowingRecord record = dataStore.getBorrowingRecords().get(fine.getBorrowingId());
                if(record != null && record.getMemberId().equals(member.getMemberId())){
                    throw new IllegalArgumentException("Member has an Unpaid fine and cannot borrow another book!!!");
                }
            }
        }
        for(BorrowingRecord record : dataStore.getBorrowingRecords().values()){
            if(record.getMemberId().equals(member.getMemberId()) && record.getBookId().equals(book.getBookId()) && record.getStatus() == BorrowingStatus.ACTIVE){
                throw new IllegalStateException("Member already borrowed this book and has not returned it.");
            }
        }
    }

    public BorrowingRecord issueBook(String bookId, String memberId, LocalDate borrowingDate){
        if (borrowingDate == null) {
            throw new IllegalArgumentException("Borrowing date cannot be null.");
        }
        if(memberId == null || memberId.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid memberId!!!");
        }
        Member member = dataStore.getMembers().get(memberId.toUpperCase().trim());
        if(member == null){
            throw new IllegalStateException("Member Doesn't exists enter valid memberId!!!");
        }
        if(bookId == null || bookId.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid BookId!!!");
        }
        Book book = dataStore.getBooks().get(bookId.toUpperCase().trim());
        if(book == null){
            throw new IllegalArgumentException("Book doesn't exists , Enter valid bookID!!!");
        }
        if(book.getAvailableCopies() <= 0){
            throw new IllegalArgumentException("Currently this book is unavailable!!!");
        }
        checkEligibility(member,book,borrowingDate);
        String borrowId = IdGenerator.generateBorrowingId();
        LocalDate dueDate = borrowingDate.plusDays(14);
        BorrowingRecord record = new BorrowingRecord(borrowId,memberId.toUpperCase(),bookId.toUpperCase(),borrowingDate,dueDate);
        book.setAvailableCopies(book.getAvailableCopies()-1);
        dataStore.getBorrowingRecords().put(borrowId,record);
        return record;
    }

    public BorrowingRecord returnBook(String borrowingId,LocalDate returnDate){
        if(borrowingId == null || borrowingId.trim().isEmpty()){
            throw new IllegalArgumentException("BorrowingId cannot be empty!!!");
        }
        BorrowingRecord record = dataStore.getBorrowingRecords().get(borrowingId.toUpperCase().trim());
        if(record == null){
            throw new IllegalStateException("No valid BorrowingRecord availavble!!!");
        }
        if(record.getStatus() == BorrowingStatus.RETURNED){
            throw new IllegalStateException("Book already Returned!!!");
        }
        Book book = dataStore.getBooks().get(record.getBookId());
        if(returnDate.isAfter(record.getDueDate())){
            double amount = fineService.calculateFineAmount(record.getDueDate(), returnDate);
            String fineId = IdGenerator.generateFineId();
            Fine fine = new Fine(fineId,borrowingId,amount);
            fineService.createFine(fine);
        }
        record.markAsReturned(returnDate);
        book.setAvailableCopies(book.getAvailableCopies()+1);
        return record;
    }

    public List<BorrowingRecord> getActiveBorrowingsByMemberId(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be empty.");
        }
        if (!dataStore.getMembers().containsKey(memberId.toUpperCase().trim())) {
            throw new IllegalStateException("Member not found.");
        }
        List<BorrowingRecord> activeBorrowings = new ArrayList<>();
        for (BorrowingRecord record : dataStore.getBorrowingRecords().values()) {
            if (record.getMemberId().equals(memberId.toUpperCase().trim()) && record.getStatus() == BorrowingStatus.ACTIVE) {
                activeBorrowings.add(record);
            }
        }
        return activeBorrowings;
    }
}

