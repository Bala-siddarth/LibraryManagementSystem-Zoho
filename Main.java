package main;
import memory.DataInitializer;
import memory.InMemoryDataStore;
import services.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        InMemoryDataStore dataStore = new InMemoryDataStore();
        DataInitializer.initialize(dataStore);
        BookService bookService = new BookService(dataStore);
        MemberService memberService = new MemberService(dataStore);
        BorrowingService borrowingService = new BorrowingService(dataStore);
        FineService fineService = new FineService(dataStore);
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        do {
            try {
                System.out.println(">>>LIBRARY MANAGEMENT SYSTEM<<<");
                System.out.println("1. Book Service");
                System.out.println("2. Member Service");
                System.out.println("3. Borrowing Service");
                System.out.println("4. Fine Service");
                System.out.println("0. Exit");
                System.out.println("<------------------------------->");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        showBookMenu(scanner, bookService);
                        break;
                    case 2:
                        showMemberMenu(scanner, memberService);
                        break;
                    case 3:
                        showBorrowingMenu(scanner, borrowingService);
                        break;
                    case 4:
                        showFineMenu(scanner, fineService);
                        break;
                    case 0:
                        System.out.println("Thank you :) ");
                        break;
                    default:
                        System.out.println("Invalid choice!!! Please try again.");
                        break;
                }
            }
            catch(InputMismatchException e){
                System.out.println("Error : Invalid Input!!!");
                scanner.nextLine();
            }
            } while (choice != 0) ;
        scanner.close();
    }

    private static void showBookMenu(Scanner scanner,BookService bookService) {
        int choice = -1;
        do {
            BookManager manager = new BookManager();
            try {
                System.out.println(">>> BOOK SERVICES MENU <<<");
                System.out.println("1. Add Book");
                System.out.println("2. Search Book By Title");
                System.out.println("3. Get Book By ID");
                System.out.println("4. Update Book Details");
                System.out.println("5. update copies");
                System.out.println("6. Show All Books");
                System.out.println("0. Back to Main Menu");
                System.out.println("<------------------------>");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        manager.addBookMenu(scanner, bookService);
                        break;
                    case 2:
                        manager.searchBookByTitleMenu(scanner, bookService);
                        break;
                    case 3:
                        manager.getBookByIdMenu(scanner, bookService);
                        break;
                    case 4:
                        manager.updateBookDetailsMenu(scanner, bookService);
                        break;
                    case 5:
                        manager.updateCopiesMenu(scanner, bookService);
                        break;
                    case 6:
                        manager.showAllBooksMenu(scanner, bookService);
                        break;
                    case 0:
                        System.out.println("Return to Main Menu...");
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                        break;
                }
            }catch(InputMismatchException e){
                System.out.println("Error : Invalid Input!!!");
                scanner.nextLine();
            }
        } while (choice != 0);
    }
    private static void showMemberMenu(Scanner scanner, MemberService memberService) {
        MemberManager manager = new MemberManager();
        int choice =-1;
        do {
            try {
                System.out.println(">>> MEMBER SERVICES MENU <<<");
                System.out.println("1. Register Member");
                System.out.println("2. Search Members By Name");
                System.out.println("3. Get Member By ID");
                System.out.println("4. Update Member Details");
                System.out.println("5. show All Members");
                System.out.println("0. Back to Main Menu");
                System.out.println("<-------------------------->");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        manager.registerMemberMenu(scanner, memberService);
                        break;
                    case 2:
                        manager.searchMembersByNameMenu(scanner, memberService);
                        break;
                    case 3:
                        manager.getMemberByIdMenu(scanner, memberService);
                        break;
                    case 4:
                        manager.updateMemberDetailsMenu(scanner, memberService);
                        break;
                    case 5 :
                        manager.getAllMembersMenu(scanner, memberService);
                        break;
                    case 0:
                        System.out.println("Returning to Main Menu...");
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                        break;
                }
            }catch(InputMismatchException e){
                System.out.println("Error : Invalid Input!!!");
            }
        } while (choice != 0);
    }

    private static void showBorrowingMenu(Scanner scanner, BorrowingService borrowingService) {
        int choice = -1;
        do {
            try {
                BorrowManager manager = new BorrowManager();
                System.out.println(">>> BORROWING SERVICES MENU <<<");
                System.out.println("1. Issue Book");
                System.out.println("2. Return Book");
                System.out.println("3. View Active Borrowings By Member ID");
                System.out.println("0. Back to Main Menu");
                System.out.println("<------------------------------------>");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        manager.issueBookMenu(scanner, borrowingService);
                        break;
                    case 2:
                        manager.returnBookMenu(scanner, borrowingService);
                        break;
                    case 3:
                        manager.viewActiveBorrowingsByMemberMenu(scanner, borrowingService);
                        break;
                    case 0:
                        System.out.println("Returning to Main Menu...");
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                        break;
                }
            }catch(InputMismatchException e){
                System.out.println("Error : Invalid Input!!!");
                scanner.nextLine();
            }
        }
        while (choice != 0);
    }
    private static void showFineMenu(Scanner scanner, FineService fineService) {
        int choice = -1;
        do {
            FineManager manager = new FineManager();
            try {
                System.out.println(">>> FINE SERVICES MENU <<<");
                System.out.println("1. Pay Fine");
                System.out.println("2. View Unpaid Fines By Member ID");
                System.out.println("0. Go Back to Main Menu");
                System.out.println("<--------------------------->");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        manager.payFineMenu(scanner, fineService);
                        break;
                    case 2:
                        manager.viewFinesByMemberMenu(scanner, fineService);
                        break;
                    case 0:
                        System.out.println("Return to Main Menu...");
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                        break;
                }
            }
            catch(InputMismatchException e){
                System.out.println("Error : Invalid input!!!");
                scanner.nextLine();
            }
        }
        while (choice != 0);
    }
}
