package domains;

public class Member {

    private String memberId;
    private String name;
    private String phoneNumber;

    public Member(String memberId, String name, String phoneNumber) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be empty.");
        }
        this.memberId = memberId.trim();
        setName(name);
        setPhoneNumber(phoneNumber);
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Member name cannot be empty.");
        }
        this.name = name.trim();
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() != 10) {
            throw new IllegalArgumentException("Phone number must contain exactly 10 digits.");
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            char digit = phoneNumber.charAt(i);
            if (!Character.isDigit(digit)) {
                throw new IllegalArgumentException("Phone number must contain only digits.");
            }
        }
        this.phoneNumber = phoneNumber.trim();
    }

    public void displayDetails() {
        System.out.print("Member ID: " + memberId+ " | ");
        System.out.print("Name: " + name+ " | ");
        System.out.println("Phone Number: " + phoneNumber+ " | ");

    }
}

