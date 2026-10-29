package memory;

import domains.Book;
import domains.Member;

public class DataInitializer {
    public static void initialize(InMemoryDataStore dataStore) {
        dataStore.getBooks().put("B001", new Book("B001", "java", "siddarth", "9049503837884",5));
        dataStore.getBooks().put("B002", new Book("B002", "python", "mukesh", "9780688338591", 3));
        dataStore.getBooks().put("B003", new Book("B003", "Cpp", "uday", "9780135739359", 4));
        dataStore.getBooks().put("B004", new Book("B004", "rush", "arun", "9780533961925", 2));
        dataStore.getBooks().put("B005", new Book("B005", "deluge", "kavin", "9739938062305", 3));
        dataStore.getBooks().put("B006", new Book("B006", "Go", "mani","8383937263837", 3));

        dataStore.getMembers().put("M001", new Member("M001", "siddarth", "6383177837"));
        dataStore.getMembers().put("M002", new Member("M002", "kishore", "9344856790"));
        dataStore.getMembers().put("M003", new Member("M003", "shine", "8072017724"));
        dataStore.getMembers().put("M004", new Member("M004", "rajesh", "8072807660"));
        dataStore.getMembers().put("M005", new Member("M005", "balaji", "6379645585"));
        System.out.println("Books and members have been initialized :)");
    }
}
