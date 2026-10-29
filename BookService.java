package services;

import domains.Book;
import memory.InMemoryDataStore;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    private final InMemoryDataStore dataStore;

    public BookService(InMemoryDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void addBook(Book book){
        if(book == null){
            throw new IllegalArgumentException("Book cannot be NULL");
        }
        for(Book existingBook : dataStore.getBooks().values()){
            if(existingBook.getIsbn().equalsIgnoreCase(book.getIsbn())){
                throw new IllegalArgumentException("ISBN already exists!!!");
            }
        }
        dataStore.getBooks().put(book.getBookId(),book);
        System.out.println(">>>ADDED BOOK DETAILS<<<");
        book.displayDetails();
        System.out.println("<-------------------------------------->");
    }

    public Book getBookById(String bookId){
        if(bookId == null || bookId.toUpperCase().trim().isEmpty()){
            throw new IllegalArgumentException("Book Id cannot be empty!!!");
        }
        Book book = dataStore.getBooks().get(bookId.toUpperCase());
        if(book == null){
            throw new IllegalArgumentException("Book doesn't exists!!!");
        }
        return book;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(dataStore.getBooks().values());
    }

    public List<Book> searchBooksByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Search title cannot be empty.");
        }
        List<Book> results = new ArrayList<>();
        for (Book book : dataStore.getBooks().values()) {
            if (book.getTitle().toLowerCase().contains(title.trim().toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    public void updateBookDetails(String bookId, String title, String author) {
        Book book = getBookById(bookId.toUpperCase());
        book.setTitle(title);
        book.setAuthor(author);
    }

    public void updateCopies(String bookId,int totalCopies){
        Book book = getBookById(bookId);
        book.setTotalCopies(totalCopies);
    }
}
