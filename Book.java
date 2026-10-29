package domains;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private String isbn;
    private int totalCopies;
    private int availableCopies;
    public Book(String bookId, String title, String author, String isbn, int totalCopies) {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be empty.");
        }
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be empty.");
        }
        if(isbn.length() != 13){
            throw new IllegalArgumentException("ISBN should contain 13 digits!!!");
        }
        for(int i=0;i<isbn.length();i++){
            if(!Character.isDigit(isbn.charAt(i))){
                throw new IllegalArgumentException("ISBN should only contains digits!!!");
            }
        }
        this.bookId = bookId.trim();
        this.isbn = isbn.trim();
        setTitle(title);
        setAuthor(author);
        setTotalCopies(totalCopies);
        this.availableCopies = totalCopies;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public int getTotalCopies(){return totalCopies;}

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be empty.");
        }
        this.title = title.trim();
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be empty.");
        }
        this.author = author.trim();
    }

    public void setTotalCopies(int totalCopies) {
        if (totalCopies <= 0) {
            throw new IllegalArgumentException("Total copies must be greater than zero.");
        }
        if (totalCopies < this.totalCopies - this.availableCopies) {
            throw new IllegalArgumentException("Total copies cannot be less than copies currently borrowed.");
        }
        this.availableCopies += totalCopies - this.totalCopies;
        this.totalCopies = totalCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        if (availableCopies < 0 || availableCopies > totalCopies) {
            throw new IllegalArgumentException("Available copies must be between 0 and total copies.");
        }
        this.availableCopies = availableCopies;
    }

    public void displayDetails() {
        System.out.print("Book ID: " + bookId + " | ");
        System.out.print("Title: " + title+  " | ");
        System.out.print("Author: " + author+  " | ");
        System.out.print("ISBN: " + isbn+ " | ");
        System.out.print("Total Copies: " + totalCopies+ " | ");
        System.out.println("Available Copies: " + availableCopies);
    }
}


