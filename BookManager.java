package main;
import domains.Book;
import services.BookService;
import services.IdGenerator;
import java.util.List;
import java.util.Scanner;

public class BookManager {
    public void addBookMenu(Scanner scanner, BookService bookService) {
        try {
            String bookId = IdGenerator.generateBookId();
            System.out.println("Enter Book Title: ");
            String title = scanner.nextLine().trim();
            System.out.println("Enter Author Name: ");
            String author = scanner.nextLine().trim();
            System.out.println("Enter ISBN: ");
            String isbn = scanner.nextLine().trim();
            System.out.println("Enter Total Copies: ");
            int totalCopies = scanner.nextInt();
            scanner.nextLine();
            Book book = new Book(bookId, title, author, isbn, totalCopies);
            bookService.addBook(book);
            System.out.println("Book added successfully!");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void searchBookByTitleMenu(Scanner scanner, BookService bookService) {
        System.out.print("Enter book title to search: ");
        String title = scanner.nextLine().trim();
        try {
            List<Book> books = bookService.searchBooksByTitle(title);
            if (books.isEmpty()) {
                System.out.println("No books found with that title.");
                return;
            }
            System.out.println(">>> Result Books <<<");
            for (Book book : books) {
                book.displayDetails();
            }
            System.out.println("<-------------------------------------------->");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getBookByIdMenu(Scanner scanner, BookService bookService) {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine().trim();
        try {
            Book book = bookService.getBookById(bookId);
            System.out.println(">>> Book details <<<");
            book.displayDetails();
            System.out.println("<------------------------------->");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateBookDetailsMenu(Scanner scanner, BookService bookService) {

        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine().trim();
        System.out.print("Enter new Book Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter new Author Name: ");
        String author = scanner.nextLine().trim();
        try {
            bookService.updateBookDetails(bookId, title, author);
            System.out.println("Book details updated successfully!");
        }
        catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void showAllBooksMenu(Scanner scanner,BookService bookService){
        List<Book> books = bookService.getAllBooks();
        if(books.isEmpty()){
            System.out.println("No books found!!!");
        }
        System.out.println(">>> ALL BOOKS DETAILS <<<");
        for(Book book : books){
            book.displayDetails();
        }
        System.out.println("<------------------------------------------>");
    }

    public void updateCopiesMenu(Scanner scanner,BookService bookService){
        System.out.println("Enter book Id : ");
        String bookId = scanner.nextLine().trim();
        System.out.println("Enter totalCopies : ");
        int totalCopies = scanner.nextInt();
        try {
            bookService.updateCopies(bookId, totalCopies);
            System.out.println("Copies updated successfully!!!");
        }
        catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
