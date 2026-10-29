package memory;

import java.util.LinkedHashMap;
import java.util.Map;

import domains.Book;
import domains.Member;
import domains.BorrowingRecord;
import domains.Fine;

public class InMemoryDataStore {

    private final Map<String, Book> books;
    private final Map<String, Member> members;
    private final Map<String, BorrowingRecord> borrowingRecords;
    private final Map<String, Fine> fines;

    public InMemoryDataStore() {
        this.books = new LinkedHashMap<>();
        this.members = new LinkedHashMap<>();
        this.borrowingRecords = new LinkedHashMap<>();
        this.fines = new LinkedHashMap<>();
    }

    public Map<String, Book> getBooks() {
        return books;
    }
    public Map<String, Member> getMembers() {
        return members;
    }
    public Map<String, BorrowingRecord> getBorrowingRecords() {
        return borrowingRecords;
    }
    public Map<String, Fine> getFines() {
        return fines;
    }
}

