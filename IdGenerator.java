package services;

public class IdGenerator {

    private static int bookCounter = 6;
    private static int memberCounter = 5;
    private static int borrowingCounter = 0;
    private static int fineCounter = 0;

    public static String generateBookId() {
        bookCounter++;
        return String.format("B%03d", bookCounter);
    }

    public static String generateMemberId() {
        memberCounter++;
        return String.format("M%03d", memberCounter);
    }

    public static String generateBorrowingId() {
        borrowingCounter++;
        return String.format("BR%03d", borrowingCounter);
    }

    public static String generateFineId() {
        fineCounter++;
        return String.format("F%03d", fineCounter);
    }
}
