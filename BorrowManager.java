package main;
import domains.BorrowingRecord;
import services.BorrowingService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Scanner;

public class BorrowManager {
    public void issueBookMenu(Scanner scanner, BorrowingService borrowingService) {
        try{
            System.out.println("Enter Book ID: ");
            String bookId = scanner.nextLine().trim();
            System.out.println("Enter Member ID: ");
            String memberId = scanner.nextLine().trim();
            System.out.print("Enter Borrowing Date (dd-MM-yyyy): ");
            String input = scanner.nextLine().trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
            LocalDate borrowingDate = LocalDate.parse(input, formatter);
            BorrowingRecord record = borrowingService.issueBook(bookId, memberId, borrowingDate);
            System.out.println(">>> BORROW DETAILS <<<");
            System.out.println("Borrowing ID: " + record.getBorrowingId());
            System.out.println("Book ID: " + record.getBookId());
            System.out.println("Member ID: " + record.getMemberId());
            System.out.println("Due Date: " + record.getDueDate());
            System.out.println("<---------------------------------->");
            System.out.println("Book issued successfully!");
        }catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date! Enter a valid date in dd-MM-yyyy format.");
        }
        catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void returnBookMenu(Scanner scanner, BorrowingService borrowingService) {
        try{
        System.out.println("Enter Borrowing ID: ");
        String borrowingId = scanner.nextLine().trim();
        System.out.print("Enter Return Date (dd-MM-yyyy): ");
        String input = scanner.nextLine().trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
        LocalDate returnDate = LocalDate.parse(input, formatter);
            BorrowingRecord record = borrowingService.returnBook(borrowingId.toUpperCase(),returnDate);
            System.out.println(">>> RETURN DETAILS <<<");
            System.out.println("Borrowing ID: " + record.getBorrowingId());
            System.out.println("Book ID: " + record.getBookId());
            System.out.println("Member ID: " + record.getMemberId());
            System.out.println("Return Date: " + record.getReturnDate());
            System.out.println("<------------------------------------->");
            System.out.println("Book returned successfully!");
        }catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date,Enter a valid date in dd-MM-yyyy format.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void viewActiveBorrowingsByMemberMenu(Scanner scanner, BorrowingService borrowingService) {
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine().trim();
        try {
            List<BorrowingRecord> records = borrowingService.getActiveBorrowingsByMemberId(memberId.toUpperCase());
            if (records.isEmpty()) {
                System.out.println("This member has no active borrowings.");
                return;
            }
            System.out.println(">>> BORROWING OF A MEMBER <<<");
            for (BorrowingRecord record : records) {
                System.out.print("Borrowing ID: " + record.getBorrowingId()+ " | ");
                System.out.print("Book ID: " + record.getBookId()+ " | " );
                System.out.print("Member ID: " + record.getMemberId()+ " | ");
                System.out.println("Due Date: " + record.getDueDate());
            }
            System.out.println("<--------------------------->");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
