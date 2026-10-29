package main;
import domains.Fine;
import services.FineService;
import java.util.List;
import java.util.Scanner;

public class FineManager {
    public void payFineMenu(Scanner scanner, FineService fineService) {
        System.out.print("Enter Fine ID: ");
        String fineId = scanner.nextLine().trim();
        try {
            fineService.payFine(fineId);
            System.out.println("Fine paid successfully!");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void viewFinesByMemberMenu(Scanner scanner, FineService fineService) {
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine().trim();
        try {
            List<Fine> fines = fineService.getFinesByMemberId(memberId);
            if (fines.isEmpty()) {
                System.out.println("No fines found for this member.");
                return;
            }
            for (Fine fine : fines) {
                System.out.println(">>> FINES BY MEMBER <<<");
                System.out.println("Fine ID: " + fine.getFineId());
                System.out.println("Borrowing ID: " + fine.getBorrowingId());
                System.out.println("Fine Amount: Rs." + fine.getFineAmount());
                System.out.println("Payment Status: " + fine.getStatus());
                System.out.println("<------------------------------->");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
